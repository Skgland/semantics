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
package de.cau.cs.kieler.sccharts.klighd.synthesis

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.ViewSynthesisShared
import java.util.Set
import de.cau.cs.kieler.core.kexpressions.ValuedObject
import com.google.inject.Inject
import de.cau.cs.kieler.sccharts.klighd.synthesis.styles.EquationStyles
import de.cau.cs.kieler.core.kexpressions.extensions.KExpressionsValuedObjectExtensions
import de.cau.cs.kieler.core.kexpressions.extensions.KExpressionsValueExtensions
import org.eclipse.emf.ecore.EObject
import de.cau.cs.kieler.core.kexpressions.Value
import de.cau.cs.kieler.core.kexpressions.ValuedObjectReference
import de.cau.cs.kieler.core.kexpressions.OperatorExpression
import de.cau.cs.kieler.sccharts.extensions.SCChartsSerializeHRExtension
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.kexpressions.VariableDeclaration
import de.cau.cs.kieler.core.kexpressions.ReferenceDeclaration
import de.cau.cs.kieler.sccharts.State
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.sccharts.klighd.layout.SidebarOverrideLayoutConfig

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import de.cau.cs.kieler.kiml.options.PortSide
import de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.kiml.options.PortConstraints
import de.cau.cs.kieler.kiml.options.PortLabelPlacement
import de.cau.cs.kieler.core.kexpressions.keffects.Assignment
import de.cau.cs.kieler.core.annotations.extensions.AnnotationsExtensions
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.emf.common.util.URI
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier
import java.util.List
import de.cau.cs.kieler.core.kexpressions.Expression

/**
 * @author ssm
 *
 */
@ViewSynthesisShared
class EquationSynthesis extends SubSetSynthesis<Assignment, KNode, Set<KNode>> {
    
    @Inject
    extension KNodeExtensionsReplacement

    @Inject
    extension KEdgeExtensions
    
    @Inject
    extension KPortExtensionsReplacement
    
    @Inject
    extension KLabelExtensions
    
    @Inject
    extension KExpressionsValuedObjectExtensions
    
    @Inject
    extension KExpressionsValueExtensions
    
    @Inject
    extension AnnotationsExtensions
    
    @Inject
    extension SCChartsSerializeHRExtension    
    
    @Inject
    extension EquationStyles
    
    @Inject
    extension DataflowRegionSynthesis
    
    @Inject
    IResourceServiceProvider.Registry regXtext;    
    
    
    private val dataSources = <EObject> newHashSet
    private val dataSinks = <ValuedObject> newHashSet
    private val dataRefs = <ValuedObject> newHashSet
    
    override performTranformationToSet(Assignment equation) {
        val nodes = <KNode> newHashSet
        
        nodes += equation.performDataSourceTransformation;
        nodes += equation.performDataSinkTransformation;
        
        val equationNodes = equation.expression.performEquationTransformation;
        
        val node = equation.valuedObject.createNode
//        node.addNodeLabel(equation.serializeAssignmentRoot.toString)
        
        val edge = equation.expression.performWireTransformation
        edge.target = node
        if (equation.valuedObject.eContainer instanceof ReferenceDeclaration) {
            if (equation.subReference != null) {
                edge.targetPort = equation.valuedObject.getPort(equation.subReference.valuedObject)
            } 
        }        
        
        nodes += equationNodes
        nodes += node
        
        
        nodes
    }
    
    private def dispatch Set<KNode> performEquationTransformation(Value value) {
        <KNode> newHashSet
    }
    
    private def dispatch Set<KNode> performEquationTransformation(ValuedObjectReference reference) {
        <KNode> newHashSet
    }
    
    private def dispatch Set<KNode> performEquationTransformation(OperatorExpression expression) {
        val result = <KNode> newHashSet
        val node = expression.createNode.associateWith(expression) => [ addOperatorNodeFigure ]
        node.addOperatorNodeLabel(expression.operator.serializeHR.toString)
        
        result += node
        
        for(se : expression.subExpressions) {
            val nodes = se.performEquationTransformation
            
            result += nodes
        }
        
        return result 
    }
    
    private def dispatch KEdge performWireTransformation(Value value) {
        val edge = createEdge.associateWith(value)
        edge.addWireFigure
        edge.source = value.getNode
        edge
    }
    
    private def dispatch KEdge performWireTransformation(ValuedObjectReference reference) {
        val edge = createEdge.associateWith(reference)
        edge.addWireFigure
        edge.source = reference.valuedObject.getNode
        
        if (reference.valuedObject.eContainer instanceof ReferenceDeclaration) {
            if (reference.subReference != null) {
                edge.sourcePort = reference.valuedObject.getPort(reference.subReference.valuedObject)
            } 
        }
        
        edge        
    }

    private def dispatch KEdge performWireTransformation(OperatorExpression expression) {
        val node = expression.getNode
        
        for(se : expression.subExpressions) {
            val edge = se.performWireTransformation
            edge.target = node
        }
        val edge = createEdge.associateWith(expression)
        edge.source = node
        edge.addWireFigure
        
        edge
    }
    
    
    protected def Set<KNode> performReferenceNodeTransformation(ValuedObject vo) {
        val result = <KNode> newHashSet
        if (dataRefs.contains(vo)) return result
        dataRefs += vo
        
        val reference = (vo.eContainer as ReferenceDeclaration).reference as State
        if (reference.hasAnnotation("actor")) {
            val kgt = reference.getStringAnnotationValue("actor")
            println(vo.eResource.URI)
            val sl = vo.eResource.URI.segmentsList
            val nsl = sl.take(sl.length - 1).drop(1)
            val newURI = URI.createPlatformResourceURI(nsl.join("/") + "/" + kgt, false)
            println(newURI)

            val provider = regXtext.getResourceServiceProvider(newURI)
            val newResourceSet = provider.get(XtextResourceSet)
            newResourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.FALSE)
            val res = newResourceSet.createResource(newURI)

            try {
                res.load(newResourceSet.loadOptions)
                val node = (res.getContents().get(0) as KNode).children.head
                vo.addNode(node)
                result += node 
            
                val vos = <ValuedObject> newArrayList
                reference.declarations.forEach[ vos += valuedObjects ]
            
                for(p : node.eAllContents.filter(KPort).toList) {
                    val id = p.data.filter(KIdentifier).head
                    val obj = vos.filter[ it.name.equals(id.id) ].head
                
                    vo.addPort(obj, p)
                }

                return result
            } catch (Exception e) {
                // Display default reference actor
            }            
        }      
        
        val node = vo.createNode => [ 
            addLayoutParam(LayoutOptions::ALGORITHM, "de.cau.cs.kieler.box");
            setLayoutOption(LayoutOptions::BORDER_SPACING, 5f);
            setLayoutOption(LayoutOptions::SPACING, 1f);
            setLayoutOption(SidebarOverrideLayoutConfig::FIXED_SPACING, 1f);
            setLayoutOption(LayoutOptions::EXPAND_NODES, true);         
            setLayoutOption(LayoutOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_ORDER) 
            setLayoutOption(LayoutOptions.PORT_LABEL_PLACEMENT, PortLabelPlacement.INSIDE)     
            addReferenceNodeFigure
        ]
        node.associateWith(vo)
        node.addNodeLabel((vo as ValuedObject).serializeHR.toString)
        node.addRegionsArea
        
        node.children += (vo as ValuedObject).createReferenceDataflowRegion
        
        for(output : reference.declarations.filter(VariableDeclaration).filter[ output ]) {
            for(v : output.valuedObjects) {
                val port = vo.createPort(v) => [
                    addLayoutParam(LayoutOptions::PORT_SIDE, PortSide.EAST)
                    setPortSize(3, 3)
                    addLayoutParam(LayoutOptions::OFFSET, -3f)
                    createLabel().configureInsidePortLabel(v.serializeHR.toString, 5)
                    node.ports += it
                ]          
                port.associateWith(v)              
            }
        }
        for(input : reference.declarations.filter(VariableDeclaration).filter[ input ]) {
            for(v : input.valuedObjects.reverseView) {
                val port = vo.createPort(v) => [
                    if (v.hasAnnotation("hidden")) {
                        addLayoutParam(LayoutOptions::PORT_SIDE, PortSide.SOUTH)
                    } else {
                        addLayoutParam(LayoutOptions::PORT_SIDE, PortSide.WEST)
                    }
                    setPortSize(3, 3)
                    addLayoutParam(LayoutOptions::OFFSET, -3f)
                    createLabel().configureInsidePortLabel(v.serializeHR.toString, 5)
                    node.ports += it
                ]          
                port.associateWith(v)              
            }
        }
        
        result += node
        
        result        
    }
    
    
    private def Set<KNode> performDataSourceTransformation(Assignment equation) {
        val result = <KNode> newHashSet
        val vors = equation.expression.getAllPrimaryReferences.map[ valuedObject ]
            + equation.expression.getAllValues
        for(vo : vors) {
            if (!dataSources.contains(vo)) {
                
                var isReference = false
                if (vo instanceof ValuedObject) {
                    if (vo.eContainer instanceof ReferenceDeclaration) {
                        if ((vo.eContainer as ReferenceDeclaration).extern == null) {
                            isReference = true
                        }
                    }
                }
                
                dataSources += vo
                if (!isReference) {
                    val node = vo.createNode => [ addInputNodeFigure ]
                    node.associateWith(vo)
                    node.addNodeLabel(vo.serializeHR.toString);
                    result += node
                } else {
                    result += (vo as ValuedObject).performReferenceNodeTransformation
                }
            }
        }
        result 
    }
    
    private def Set<KNode> performDataSinkTransformation(Assignment equation) {
        val result = <KNode> newHashSet
        val vo = equation.valuedObject
        
        if (vo.eContainer instanceof ReferenceDeclaration) {
            if ((vo.eContainer as ReferenceDeclaration).extern == null) {
                return vo.performReferenceNodeTransformation
            }
        }
        
        if (!dataSinks.contains(vo)) {
            dataSinks += vo
            val node = vo.createNode => [ addOutputNodeFigure ]
            node.associateWith(vo)
            node.addNodeLabel(vo.serializeHR.toString);
            result += node
        }
        result 
    }
    
    def List<ValuedObjectReference> getAllPrimaryReferences(Expression expression) {
        <ValuedObjectReference> newArrayList => [
            if (expression == null) {
            } else if (expression instanceof ValuedObjectReference) { 
                it += expression
            } else { 
                it += expression.eAllContents.filter(ValuedObjectReference).
                filter[ !(eContainer instanceof ValuedObjectReference) ].toList
            }
        ]  
    }     
    
}