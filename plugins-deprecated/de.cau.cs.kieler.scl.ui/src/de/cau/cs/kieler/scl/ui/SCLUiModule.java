package de.cau.cs.kieler.scl.ui;

/**
 * @author ssm
 *
 */

/*
 * Auto-generated Xtext code edited to bind semantic highlighting.
 */

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

/**
 * Use this class to register components to be used within the IDE.
 */
public class SCLUiModule extends de.cau.cs.kieler.scl.ui.AbstractSCLUiModule {
	public SCLUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}
	
	 public Class<? extends IHighlightingConfiguration> bindIHighlightingConfiguration () {
	     return SCLHighlightingConfiguration.class;
	 }
	 
	 public Class<? extends ISemanticHighlightingCalculator> bindISemanticHighlightingCalculator(){
	     return SCLSemanticHighlightingCalculator.class;
	 }

}