/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.sccharts.transformations

import com.google.common.collect.Sets
import com.google.inject.Inject
import de.cau.cs.kieler.kico.transformation.AbstractExpansionTransformation
import de.cau.cs.kieler.kitt.tracing.Traceable
import de.cau.cs.kieler.sccharts.State
import de.cau.cs.kieler.sccharts.StateType
import de.cau.cs.kieler.sccharts.extensions.SCChartsExtension
import de.cau.cs.kieler.sccharts.featuregroups.SCChartsFeatureGroup
import de.cau.cs.kieler.sccharts.features.SCChartsFeature

/**
 * SCCharts Connector Transformation.
 * 
 * @author cmot
 * @kieler.design 2013-09-05 proposed 
 * @kieler.rating 2013-09-05 proposed yellow
 */
class Connector extends AbstractExpansionTransformation implements Traceable {

    //-------------------------------------------------------------------------
    //--                 K I C O      C O N F I G U R A T I O N              --
    //-------------------------------------------------------------------------
    override getId() {
        return SCChartsTransformation::CONNECTOR_ID
    }

    override getName() {
        return SCChartsTransformation::CONNECTOR_NAME
    }

    override getExpandsFeatureId() {
        return SCChartsFeature::CONNECTOR_ID
    }

    override getProducesFeatureIds() {
        return Sets.newHashSet()
    }

    override getNotHandlesFeatureIds() {
        return Sets.newHashSet(SCChartsFeatureGroup::EXPANSION_ID)
    }

    //-------------------------------------------------------------------------
    @Inject
    extension SCChartsExtension

    // This prefix is used for naming of all generated signals, states and regions
    static public final String GENERATED_PREFIX = "_"

    //-------------------------------------------------------------------------
    //--                       C O N N E C T O R S                           --
    //-------------------------------------------------------------------------
    // Turn every connector into a simple state and turn all outgoing 
    // transitions into immediate transitions.
    def State transform(State rootState) {
        val targetRootState = rootState.fixAllPriorities;

        // Traverse all states
        targetRootState.allStates.forEach [ targetTransition |
            targetTransition.transformConnector(targetRootState);
        ]

        //        for (targetTransition : targetRootState.allStates) {
        //            targetTransition.transformConnector(targetRootState);
        //        }
        targetRootState.fixAllTextualOrdersByPriorities;
    }

    def void transformConnector(State state, State targetRootState) {
        if (state.type == StateType::CONNECTOR) {
            state.setTypeNormal
            for (transition : state.outgoingTransitions) {
                transition.setImmediate(true)
            }
        }
    }

}