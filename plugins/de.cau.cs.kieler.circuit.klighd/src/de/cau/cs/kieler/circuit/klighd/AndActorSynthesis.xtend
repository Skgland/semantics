package de.cau.cs.kieler.circuit.klighd

import com.google.inject.Inject
import de.cau.cs.kieler.circuit.Actor
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.KlighdConstants

class AndActorSynthesis implements IDrawableActor  {
	@Inject
    extension KNodeExtensions
	@Inject extension KLabelExtensions
    
    
    
    
	
	override draw(Actor actor) {
		val KNode andNode = actor.node
		
		andNode.setNodeSize(40, 40);
		andNode.addInsideBottomCenteredNodeLabel("&", KlighdConstants.DEFAULT_FONT_SIZE,
				KlighdConstants.DEFAULT_FONT_NAME);

		
		return andNode;
	}
	
}