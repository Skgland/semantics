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
package de.cau.cs.kieler.annotations.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;

/**
 * This class contains custom scoping description.
 * 
 * See {@link http://www.eclipse.org/Xtext/documentation/latest/xtext.html#scoping}
 * on how and when to use it.
 * 
 * @author chsch
 */
public class AnnotationsScopeProvider extends AbstractDeclarativeScopeProvider {
    
    public IScope getScope(EObject context, EReference reference) {
        return super.getScope(context, reference);
    }

}