/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.scg.ssc.features

import de.cau.cs.kieler.kico.features.Feature
import de.cau.cs.kieler.scg.ssc.ssa.SSACoreExtensions
import de.cau.cs.kieler.scl.scl.SCLProgram
import javax.inject.Inject

/**
 * The SSA feature fore SCGs.
 * 
 * @author als
 * @kieler.design proposed
 * @kieler.rating proposed yellow
 */
class SSASCLFeature extends Feature {
    
    public static val ID = "scl.ssa"
    
    //-------------------------------------------------------------------------
    //--                 K I C O      C O N F I G U R A T I O N              --
    //-------------------------------------------------------------------------
    override getId() {
        return ID
    }

    override getName() {
        return "SSA SCL"
    }

    //-------------------------------------------------------------------------
    @Inject
    extension SSACoreExtensions

    // This method checks, if this feature is contained in a model
    def isContained(SCLProgram p) {
        return p.declarations.exists[isSSA]
    }
    
}