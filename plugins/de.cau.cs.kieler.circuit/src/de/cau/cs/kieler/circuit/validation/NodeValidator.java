/**
 *
 * $Id$
 */
package de.cau.cs.kieler.circuit.validation;

import de.cau.cs.kieler.circuit.Port;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link de.cau.cs.kieler.circuit.Node}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface NodeValidator {
	boolean validate();

	boolean validateInputPorts(EList<Port> value);
	boolean validateOutputPorts(EList<Port> value);
	boolean validateNodeID(String value);
}