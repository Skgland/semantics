/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.synccharts.text.actions.bridge;

import java.io.IOException;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

import com.google.inject.Injector;

import de.cau.cs.kieler.core.kexpressions.ValuedObject;
import de.cau.cs.kieler.core.model.KielerModelException;
import de.cau.cs.kieler.core.model.util.PossiblyEmptyCompoundCommand;
import de.cau.cs.kieler.synccharts.Action;
import de.cau.cs.kieler.synccharts.Region;
import de.cau.cs.kieler.synccharts.State;
import de.cau.cs.kieler.synccharts.Transition;
import de.cau.cs.kieler.synccharts.text.actions.ActionsStandaloneSetup;

/**
 * A wrapper class that provides methods to process (i.e. parse or serialize)
 * Action labels (i.e. transition labels or entry, inner, exit, suspend actions
 * of states).
 * 
 * @author haf
 * @kieler.ignore (excluded from review process)
 */
public class ActionLabelProcessorWrapper {

    /** Constant denoting the 'parse' option. */
    public static final boolean PARSE = true;

    /** Constant denoting the 'serialize' option. */
    public static final boolean SERIALIZE = false;

    private Injector injector;

    /**
     * Default Constructor initializes parsers and serializers.
     */
    public ActionLabelProcessorWrapper() {
        injector = new ActionsStandaloneSetup()
                .createInjectorAndDoEMFRegistration();
    }

    /**
     * Recursively parse or serialize all Action objects starting from the
     * parent object and recursively calling it to child states resp. child
     * regions. All state actions (entry, exit, inner, suspension) and all
     * transitions will be serialized or parsed again. If the parent is null,
     * the parent is determined by the container object of the given
     * Signal/Variable. If the changed object is null, then all children of the
     * parent will be processed. If both are null, this cannot be handled and
     * will silently return.
     * 
     * @param changedObject
     *            the Signal/Variable that has changed its name
     * @param theParent
     *            the current parent object, either State or Region
     * @param parse
     *            true if the label should be reparsed, false if it should be
     *            serialized
     * @throws IOException
     *             unlikely parser IO error
     * @throws KielerModelException
     *             possible parser syntax errors
     */
    public void processAffectedActionLabels(final ValuedObject changedObject,
            final EObject theParent, final boolean parse)
            throws KielerModelException, IOException {
        EObject parent = theParent;
        if (changedObject == null && parent == null) {
            return; // cannot handle this combination
        }
        if (parent == null) {
            parent = changedObject.eContainer();
            if (parent != null) {
                processAffectedActionLabels(changedObject, parent, parse);
            }
            return;
        }

        if (parent instanceof State) {
            for (Action action : ((State) parent).getEntryActions()) {
                processAction(action, parse);
            }
            for (Action action : ((State) parent).getExitActions()) {
                processAction(action, parse);
            }
            for (Action action : ((State) parent).getInnerActions()) {
                processAction(action, parse);
            }
            if (((State) parent).getSuspensionTrigger() != null) {
                processAction(((State) parent).getSuspensionTrigger(), parse);
            }
            // do a recursive call
            for (Region childRegion : ((State) parent).getRegions()) {
                processAffectedActionLabels(changedObject, childRegion, parse);
            }
        } else if (parent instanceof Region) {
            for (State childState : ((Region) parent).getStates()) {
                for (Transition trans : childState.getOutgoingTransitions()) {
                    processAction(trans, parse);
                }
                processAffectedActionLabels(changedObject, childState, parse);
            }

        }
    }

    /**
     * A customized {@link Command} that is composed of several single commands, each of them being
     * in charge of parse/serialize a single action of a SyncCharts
     * {@link de.cau.cs.kieler.synccharts.Scope Scope} and its children.
     * 
     * @param changedObject
     *            {@link ValuedObject} on which a change may occurred
     * @param theParent
     *            the context to start the action search
     * @param parse
     *            true, if parsing, false if serializing
     * @return the {@link Command}
     */
    public Command getProcessAffectedActionLabelCommand(
            final ValuedObject changedObject, final EObject theParent,
            final boolean parse) {
        EObject parent = theParent;
        PossiblyEmptyCompoundCommand cc = new PossiblyEmptyCompoundCommand();
        if (changedObject == null && parent == null) {
            return UnexecutableCommand.INSTANCE; // cannot handle this
                                                 // combination
        }
        if (parent == null) {
            parent = changedObject.eContainer();
            if (parent != null) {
                return getProcessAffectedActionLabelCommand(changedObject,
                        parent, parse);
            }
        }
        if (parent instanceof State) {
            for (Action action : ((State) parent).getEntryActions()) {
                cc.append(getProcessActionCommand(action, parse));
            }
            for (Action action : ((State) parent).getExitActions()) {
                cc.append(getProcessActionCommand(action, parse));
            }
            for (Action action : ((State) parent).getInnerActions()) {
                cc.append(getProcessActionCommand(action, parse));
            }
            if (((State) parent).getSuspensionTrigger() != null) {
                cc.append(getProcessActionCommand(((State) parent)
                        .getSuspensionTrigger(), parse));
            }
            // do a recursive call
            for (Region childRegion : ((State) parent).getRegions()) {
                cc.append(getProcessAffectedActionLabelCommand(changedObject,
                        childRegion, parse));
            }
        } else if (parent instanceof Region) {
            for (State childState : ((Region) parent).getStates()) {
                for (Transition trans : childState.getOutgoingTransitions()) {
                    cc.append(getProcessActionCommand(trans, parse));
                }
                cc.append(getProcessAffectedActionLabelCommand(changedObject,
                        childState, parse));
            }
        }
        return cc;
    }

    /**
     * Trigger the parsing or serialization of Action labels of a given scope.
     * Starting from a given parent (i.e. State or Region) recursively iterate
     * all Actions contained, i.e. all transitions and all entry, inner, exit
     * and suspension actions. Parse/Serialize the found actions. Execute in a
     * GMF command on the model's command stack to get access to the model.
     * 
     * @param parent
     *            the context to start the action search
     * @param parse
     *            true, if parsing, false if serializing
     * @throws Exception
     *             if parsing has failed
     */
    public static void processActionLabels(final EObject parent,
            final boolean parse) throws Exception {
        TransactionalEditingDomain domain = TransactionUtil
                .getEditingDomain(parent);
        final ActionLabelProcessorWrapper parser = new ActionLabelProcessorWrapper();
        ActionLabelProcessCommand cmd = new ActionLabelProcessCommand(
                parent, parse, parser);
        CommandStack stack = domain.getCommandStack();
        stack.execute(cmd);
        if (cmd.isError() && cmd.getException() != null) {
            throw cmd.exception;
        }
    }

    /**
     * Parse or serialize an action. Serialize an Action and store the String
     * result in the Action's TriggerAndEffects String attribute. Alternatively
     * parse the TriggerAndEffects String and build the corresponding effects
     * and trigger objects and add/replace them in the Action.
     * 
     * @param action
     *            the action to be serialized/parsed
     * @throws IOException
     *             unlikely parser IO error
     * @throws KielerModelException
     *             possible parser syntax errors
     */
    void processAction(final Action action, final boolean parse)
            throws KielerModelException, IOException {
        if (parse == PARSE) {
            ActionLabelParseCommand cmd = new ActionLabelParseCommand(action,
                    action.getLabel(), injector);
            cmd.parse();
        } else {

            // only serialize if the action represents something useful
            if (action.getTrigger() != null || !action.getEffects().isEmpty()) {
                String newLabel = ActionLabelSerializer.toString(action);
                if (newLabel != null) {
                    action.setLabel(newLabel);
                }
            }
        }
    }

    /**
     * A customized {@link Command} that is composed of several single commands, each of them being
     * in charge of parse/serialize a single action of a SyncCharts {@link Scope} and its children.
     * 
     * @param action
     *            the {@link Action} to be parse or/and serialized.
     * @param parse
     *            true, if parsing, false if serializing
     * @return the {@link Command}
     */
    public Command getProcessActionCommand(final Action action, final boolean parse) {
        if (parse == PARSE) {
            return new ActionLabelParseCommand(action, action
                    .getLabel(), injector);
        } else {
            return new ActionLabelSerializeCommand(action);
        }
    }

    /**
     * Helper Command that executes the
     * {@link ActionLabelProcessorWrapper#processAffectedActionLabels(ValuedObject, EObject, boolean)}
     * method. Can be used execute the method on a GMF command stack to get
     * proper access to the model.
     * 
     * @author haf
     * 
     */
    private static class ActionLabelProcessCommand extends AbstractCommand {

        private ActionLabelProcessorWrapper parser;
        private EObject parent;
        private boolean parse;

        public ActionLabelProcessCommand(final EObject theParent,
                final boolean isParse,
                final ActionLabelProcessorWrapper theParser) {
            this.parent = theParent;
            this.parse = isParse;
            this.parser = theParser;
        }

        private boolean error = false;
        private Exception exception;

        public boolean isError() {
            return error;
        }

        public Exception getException() {
            return exception;
        }

        public void execute() {
            try {
                parser.processAffectedActionLabels(null, parent, parse);
            } catch (Exception e) {
                error = true;
                exception = e;
            }
        }

        public void redo() {
            execute();
        }

        @Override
        public boolean canUndo() {
            return false;
        }

        @Override
        protected boolean prepare() {
            return true;
        }
    };
}