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
package de.cau.cs.kieler.scg.priorities

import de.cau.cs.kieler.kico.AbstractKielerCompilerAuxiliaryData
import de.cau.cs.kieler.scg.Node
import java.util.LinkedList

/**
 * Class that contains auxiliary data in the form of strongly connected components.
 * Contains a list of lists of nodes that represent a strongly connected component as well as 
 * a hash map that maps each node to a strongly connected component if the node is in one.
 * @author lpe
 *
 */
class PriorityAuxiliaryData extends AbstractKielerCompilerAuxiliaryData {
    
    public static final String OPTIMIZED_NODE_PRIORITIES_ANNOTATION = "optPrioIDs"
    public static final String NODE_PRIORITIES_ANNOTATION = "nodePrios"
    public static final String THREAD_SEGMENT_ANNOTATION = "threadSegmentIDs"
    public static final String MAX_PRIO_ID = "maxPrioID"
    
    private LinkedList<LinkedList<Node>> stronglyConnectedComponents
    
    /**
     *  Returns all Strongly Connected Components of a SCG
     */
    public def LinkedList<LinkedList<Node>> getStronglyConnectedComponents() {
        stronglyConnectedComponents
    }
    
    /**
     *  Saves the Strongly Connected Components of a SCG for later use
     */
    public def void setStronglyConnectedComponents(LinkedList<LinkedList<Node>> scc) {
        this.stronglyConnectedComponents = scc
    }
}