package de.cau.cs.kieler.sccharts.klighd

import de.cau.cs.kieler.core.krendering.KColor
import de.cau.cs.kieler.core.krendering.KRenderingFactory

class SCChartsDiagramSynthesisStateStyles {
	private static final KRenderingFactory RENDERING_FACTORY = KRenderingFactory.eINSTANCE;
	
	protected static val KColor KEYWORD = RENDERING_FACTORY.createKColor() => [it.red = 115; it.green = 0; it.blue = 65];
	
    protected static val int MINIMALNODEWIDTH = 40
    protected static val int MINIMALNODEHEIGHT = 40

    protected static val int PORTFONTSIZE = 10
    protected static val int LABELFONTSIZE = 10
	
	
    protected static val KColor STATE_STARTGRADIENTCOLOR = RENDERING_FACTORY.createKColor() =>
        [it.red = 248; it.green = 249; it.blue = 253];
    protected static val KColor STATE_ENDGRADIENTCOLOR = RENDERING_FACTORY.createKColor() =>
        [it.red = 205; it.green = 220; it.blue = 243];
    protected static val KColor STATE_SUPERSTATEBACKGROUND = RENDERING_FACTORY.createKColor() =>
        [it.red = 240; it.green = 240; it.blue = 240];
        
    protected static val int DEFAULT_CORNER_RADIUS = 8
    protected static val int DEFAULT_LINE_WIDTH = 1
}