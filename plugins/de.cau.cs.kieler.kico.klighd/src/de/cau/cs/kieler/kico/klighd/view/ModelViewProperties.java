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
package de.cau.cs.kieler.kico.klighd.view;

import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Class holding the Properties used by the ModelView.
 * 
 * @author als
 * @kieler.design 2015-07-21 proposed
 * @kieler.rating 2015-07-21 proposed yellow
 * 
 */
public final class ModelViewProperties {

    /**
     * Prevent Instanciation
     */
    private ModelViewProperties() {
    }

    public static final IProperty<IEditorPart> EDITOR_PART = new Property<IEditorPart>(
            "de.cau.cs.kieler.kico.klighd.view.editor", null);
}