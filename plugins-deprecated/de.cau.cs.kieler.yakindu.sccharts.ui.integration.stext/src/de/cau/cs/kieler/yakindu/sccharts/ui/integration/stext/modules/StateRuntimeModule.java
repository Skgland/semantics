package de.cau.cs.kieler.yakindu.sccharts.ui.integration.stext.modules;

import org.eclipse.xtext.parser.IParser;

import de.cau.cs.kieler.yakindu.sccharts.model.stext.SynctextRuntimeModule;
import de.cau.cs.kieler.yakindu.sccharts.ui.integration.stext.parsers.StateAntlrParser;

public class StateRuntimeModule extends SynctextRuntimeModule {
	@Override
	public Class<? extends IParser> bindIParser() {
		return StateAntlrParser.class;
	}
}