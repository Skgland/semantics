/**
 */
package de.cau.cs.kieler.kvis.kvis;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kvis.kvis.AttributeMapping#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kvis.kvis.AttributeMapping#getLiteral <em>Literal</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kvis.kvis.AttributeMapping#getMappings <em>Mappings</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.kvis.kvis.KvisPackage#getAttributeMapping()
 * @model
 * @generated
 */
public interface AttributeMapping extends EObject
{
  /**
   * Returns the value of the '<em><b>Attribute</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute</em>' attribute.
   * @see #setAttribute(String)
   * @see de.cau.cs.kieler.kvis.kvis.KvisPackage#getAttributeMapping_Attribute()
   * @model
   * @generated
   */
  String getAttribute();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kvis.kvis.AttributeMapping#getAttribute <em>Attribute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute</em>' attribute.
   * @see #getAttribute()
   * @generated
   */
  void setAttribute(String value);

  /**
   * Returns the value of the '<em><b>Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Literal</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Literal</em>' attribute.
   * @see #setLiteral(String)
   * @see de.cau.cs.kieler.kvis.kvis.KvisPackage#getAttributeMapping_Literal()
   * @model
   * @generated
   */
  String getLiteral();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kvis.kvis.AttributeMapping#getLiteral <em>Literal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Literal</em>' attribute.
   * @see #getLiteral()
   * @generated
   */
  void setLiteral(String value);

  /**
   * Returns the value of the '<em><b>Mappings</b></em>' containment reference list.
   * The list contents are of type {@link de.cau.cs.kieler.kvis.kvis.Mapping}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mappings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mappings</em>' containment reference list.
   * @see de.cau.cs.kieler.kvis.kvis.KvisPackage#getAttributeMapping_Mappings()
   * @model containment="true"
   * @generated
   */
  EList<Mapping> getMappings();

} // AttributeMapping