/*
 * generated by Xtext
 */
package de.cau.cs.kieler.synccharts.text.kitsState.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import de.cau.cs.kieler.synccharts.text.kitsState.ui.internal.KitsStateActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class KitsStateExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return KitsStateActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return KitsStateActivator.getInstance().getInjector(KitsStateActivator.DE_CAU_CS_KIELER_SYNCCHARTS_TEXT_KITSSTATE_KITSSTATE);
	}
	
}