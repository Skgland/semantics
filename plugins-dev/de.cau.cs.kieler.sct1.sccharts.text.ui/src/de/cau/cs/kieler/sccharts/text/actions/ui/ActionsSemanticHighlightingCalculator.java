/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.sccharts.text.actions.ui;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

import de.cau.cs.kieler.annotations.ui.AnnotationsSemanticHighlightingCalculator;
import de.cau.cs.kieler.kexpressions.ui.KExpressionsSemanticHighlightingCalculator;

/**
 * Custom {@link ISemanticHighlightingCalculator} contributing to the Actions editor.
 * The required method {@link ISemanticHighlightingCalculator#provideHighlightingFor(XtextResource, IHighlightedPositionAcceptor)}
 * will be provided by the {@link AnnotationsSemanticHighlightingCalculator} for efficiency purposes
 * and delegates to {@link AnnotationsSemanticHighlightingCalculator#provideHighlightingFor(AbstractNode, IHighlightedPositionAcceptor)}
 * that can be specialized in order to added Actions specific highlighting configurations.
 *
 * The Actions specific stuff is used in the KITS-View, a plain actions editor is not provided.
 *
 * @author chsch ssm
 */
public class ActionsSemanticHighlightingCalculator extends KExpressionsSemanticHighlightingCalculator {

//    @Inject
//    private ActionsGrammarAccess g;
//
//    public void provideHighlightingFor(AbstractNode node, IHighlightedPositionAcceptor acceptor) {
//       // EObject grammarElement = node.getGrammarElement();
//       super.provideHighlightingFor(node, acceptor);
//    }

}