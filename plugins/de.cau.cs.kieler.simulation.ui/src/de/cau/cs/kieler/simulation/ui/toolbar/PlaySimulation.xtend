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
package de.cau.cs.kieler.simulation.ui.toolbar

import de.cau.cs.kieler.simulation.core.SimulationManager
import de.cau.cs.kieler.simulation.ui.SimulationConsole
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException

/**
 * @author aas
 *
 */
class PlaySimulation extends SimulationToolbarButton {
    
    override execute(ExecutionEvent event) throws ExecutionException {
        super.execute(event)
        if(SimulationManager.instance != null) {
            SimulationConsole.writeToConsole("Play")
            simulation.play()
        }
        return null
    }
}