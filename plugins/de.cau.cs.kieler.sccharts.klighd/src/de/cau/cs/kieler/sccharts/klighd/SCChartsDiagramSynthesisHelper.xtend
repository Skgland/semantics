package de.cau.cs.kieler.sccharts.klighd

import de.cau.cs.kieler.sccharts.State
import com.google.inject.Inject
import de.cau.cs.kieler.sccharts.extensions.SCChartsExtension
import java.util.List
import de.cau.cs.kieler.core.kexpressions.ValuedObject
import de.cau.cs.kieler.core.krendering.KRectangle
import org.eclipse.emf.ecore.EObject
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.core.krendering.KColor
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.KRenderingFactory

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension org.eclipse.emf.ecore.util.EcoreUtil.*


class SCChartsDiagramSynthesisHelper {
    
    @Inject
    extension KColorExtensions

    @Inject
    extension KRenderingExtensions

    @Inject
    extension KContainerRenderingExtensions        
    
    @Inject
    extension SCChartsExtension
    
    private static final KRenderingFactory RENDERING_FACTORY = KRenderingFactory.eINSTANCE;    
    
    protected static val KColor KEYWORD = RENDERING_FACTORY.createKColor() => [it.red = 115; it.green = 0; it.blue = 65];    
    
    def boolean hasNoControlflowRegionsWithStates(State state) {
        return (state.controlflowRegions == null || state.controlflowRegions.size == 0 || !state.controlflowRegionsNotEmpty)
    }
    
    def boolean hasNoDataflowRegions(State state) {
    	val df = state.dataflowRegions
    	return (df == null || df.size == 0)
    }

//    // Tells if the state needs a macro state rendering because of regions or declarations.
//    def boolean hasRegionsOrDeclarations(State state, List<ValuedObject> valuedObjectCache) {
//        val returnValue = (!state.hasNoControlflowRegionsWithStates || !state.hasNoDataflowRegions || 
//            (!state.localActions.nullOrEmpty && SHOW_STATE_ACTIONS.booleanValue) ||
//            (!valuedObjectCache.nullOrEmpty && SHOW_SIGNAL_DECLARATIONS.booleanValue))
//        return returnValue
//    }
    
//        def dispatch String getActionName(LocalAction action) {
//        // will be serialized automatically from grammar (iff not suspend action!)
//        return ""
//    }

//    // Return the action name suspend.
//    def dispatch String getActionName(SuspendAction action) {
//        if (!(action as SuspendAction).weak) {
//        "suspend "
//        } else {
//        "weak suspend "
//        }
//    }
    
        // Keyword highlighting in declarations
    // Returns true if the text is a keyword.
    def boolean isKeyword(String text) {
        return (text == "scchart") || (text == "entry") || (text == "during") || (text == "suspend") || (text == "weak") ||
            (text == "exit") || (text == "signal") || (text == "int") || (text == "bool") ||
            (text == "float") || (text == "unsigned") || (text == "immediate") || (text == "input") ||
            (text == "output") || (text == "pre") || (text == "val") || (text == "combine") || (text == "static") || 
            (text == "const") || (text == "extern") || (text == "iterate")
    }

    // Get a list of words of a text String parsed by a regular expression.
    def List<String> getWords(String text) {
        text.split("\\W+")
    }

    // Print the highlighted text into a parent and link it to a lookup EObject.
    def KRectangle printHighlightedText(KRectangle parent, String text, EObject lookup) {
//        if (USE_ADAPTIVEZOOM.booleanValue) parent.lowerVisibilityScaleBound = 0.5f;           FIX!
        var remainingText = text
        var split = ""
        val words = text.getWords
        parent.setGridPlacement(words.length + 1)
        for (word : words) {
            val index = remainingText.indexOf(word)
            split = remainingText.substring(0, index)
            remainingText = remainingText.substring(index + word.length, remainingText.length)
            parent.addText(split + word) => [
//                if (USE_ADAPTIVEZOOM.booleanValue) it.lowerVisibilityScaleBound = 0.5f;       FIX!
                if (word.keyword) {
                    it.setForeground(KEYWORD.copy)
//                    if (PAPER_BW.booleanValue || globalBWOption) {                            FIX
//                        it.setForeground("black".color)    
//                    }
                    it.setFontBold(true)
                }
//                it.putToLookUpWith(lookup)                                                    FIX
                it.setGridPlacementData()
            ]
        }
        val remainingText2 = remainingText
        if (remainingText2.length > 0) {
            parent.addText(remainingText2) => [
//                if (USE_ADAPTIVEZOOM.booleanValue) it.lowerVisibilityScaleBound = 0.5f;       FIX
                if (remainingText2.keyword) {
                    it.setForeground(KEYWORD.copy)
//                    if (PAPER_BW.booleanValue || globalBWOption) {                            FIX
//                        it.setForeground("black".color)    
//                    }
                    it.setFontBold(true)
                }
//                it.putToLookUpWith(lookup)                                                    FIX
                it.setGridPlacementData()
            ]
        }
        parent
    }
}