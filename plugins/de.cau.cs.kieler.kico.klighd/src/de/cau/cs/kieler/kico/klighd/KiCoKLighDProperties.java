/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kico.klighd;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kico.CompilationResult;

/**
 * @author als
 * @kieler.design 2014-09-22 proposed
 * @kieler.rating 2014-09-22 proposed yellow
 * 
 */
public final class KiCoKLighDProperties {
    private KiCoKLighDProperties() {
    }

    public static final IProperty<CompilationResult> COMPILATION_RESULT =
            new Property<CompilationResult>("de.cau.cs.kieler.kico.klighd.compilation.result", null);
}