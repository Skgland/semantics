/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.annotations.text;

/**
 * Initialization support for running Xtext languages without equinox extension registry.
 * 
 * @author chsch
 */
public class AnnotationsStandaloneSetup extends AnnotationsStandaloneSetupGenerated {
    /**
     * Initializes everything.
     */
    public static void doSetup() {
        new AnnotationsStandaloneSetup().createInjectorAndDoEMFRegistration();
    }
}
