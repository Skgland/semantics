/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.scl.ui;

/**
 * @author ssm
 *
 */

import java.util.Iterator;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

import de.cau.cs.kieler.scl.scl.Annotation;
import de.cau.cs.kieler.scl.scl.SclPackage;
import de.cau.cs.kieler.scl.scl.util.SclSwitch;

public class SCLSemanticHighlightingCalculator implements ISemanticHighlightingCalculator {

    public void provideHighlightingFor( XtextResource resource, IHighlightedPositionAcceptor acceptor ) {
        if( resource == null ) {
            return;
        }

        HighlightingSwitch switcher = new HighlightingSwitch( acceptor );

        Iterator<EObject> iter = EcoreUtil.getAllContents(resource, true);
        while(iter.hasNext()) {
            EObject current = iter.next();

            switcher.doSwitch( current );
        }
    }

    private class HighlightingSwitch extends SclSwitch<Void> {

        private final IHighlightedPositionAcceptor acceptor;

        public HighlightingSwitch( IHighlightedPositionAcceptor acceptor ) {
            this.acceptor = acceptor;
        }

        @Override
        public Void caseAnnotation( Annotation object ) {
            INode node = getFirstFeatureNode( object, SclPackage.eINSTANCE.getAnnotation_Name() );
            highlightNode( node, SCLHighlightingConfiguration.ANNOTATION );
            return null;
        }

        private void highlightNode( INode node, String id ) {
            if( node == null )
                return;
            if( node instanceof ILeafNode ) {
                acceptor.addPosition( node.getOffset(), node.getLength(), id );
            } else {
                for( ILeafNode leaf : node.getLeafNodes() ) {
                    if( !leaf.isHidden() ) {
                        acceptor.addPosition( leaf.getOffset(), leaf.getLength(), id );
                    }
                }
            }
        }
    }

    public INode getFirstFeatureNode( EObject semantic, EStructuralFeature feature ) {
        if( feature == null )
            return NodeModelUtils.findActualNodeFor( semantic );
        List<INode> nodes = NodeModelUtils.findNodesForFeature( semantic, feature );
        if( !nodes.isEmpty() )
            return nodes.get( 0 );
        return null;
    }    

}