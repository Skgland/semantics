/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.sccharts.debug;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.LineBreakpoint;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author lgr
 *
 */
public class TextLineBrekpointTarget implements IToggleBreakpointsTarget {

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection) {
        IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor();
        if (editor != null) {
            IResource resource = (IResource) editor.getEditorInput().getAdapter(IResource.class);
            ITextSelection textSelection = (ITextSelection) selection;
            int lineNumber = textSelection.getStartLine();
            IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager()
                    .getBreakpoints(SCChartsDebugModelPresentation.ID);

            for (int i = 0; i < breakpoints.length; i++) {
                IBreakpoint breakpoint = breakpoints[i];
                if (resource.equals(breakpoint.getMarker().getResource())) {
                    int bl;
                    try {
                        bl = ((LineBreakpoint) breakpoint).getLineNumber();  
                        if (bl == (lineNumber + 1)) {
                            breakpoint.delete();
                            return;
                        }
                    } catch (CoreException e) {
                        e.printStackTrace();
                    }
                    
                }
            }
            SCChartsBreakpoint breakpoint;
            try {
                breakpoint = new SCChartsBreakpoint(resource, lineNumber + 1);
                DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(breakpoint);
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canToggleLineBreakpoints(IWorkbenchPart part, ISelection selection) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleMethodBreakpoints(IWorkbenchPart part, ISelection selection)
            throws CoreException {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canToggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleWatchpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canToggleWatchpoints(IWorkbenchPart part, ISelection selection) {
        // TODO Auto-generated method stub
        return false;
    }

}