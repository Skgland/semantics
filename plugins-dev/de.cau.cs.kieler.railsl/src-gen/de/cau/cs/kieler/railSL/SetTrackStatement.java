/**
 * generated by Xtext 2.10.0
 */
package de.cau.cs.kieler.railSL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Track Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.railSL.SetTrackStatement#getSegments <em>Segments</em>}</li>
 *   <li>{@link de.cau.cs.kieler.railSL.SetTrackStatement#getMode <em>Mode</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.railSL.RailSLPackage#getSetTrackStatement()
 * @model
 * @generated
 */
public interface SetTrackStatement extends SetStatement
{
  /**
   * Returns the value of the '<em><b>Segments</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Segments</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Segments</em>' attribute list.
   * @see de.cau.cs.kieler.railSL.RailSLPackage#getSetTrackStatement_Segments()
   * @model unique="false"
   * @generated
   */
  EList<String> getSegments();

  /**
   * Returns the value of the '<em><b>Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mode</em>' attribute.
   * @see #setMode(String)
   * @see de.cau.cs.kieler.railSL.RailSLPackage#getSetTrackStatement_Mode()
   * @model
   * @generated
   */
  String getMode();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.railSL.SetTrackStatement#getMode <em>Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mode</em>' attribute.
   * @see #getMode()
   * @generated
   */
  void setMode(String value);

} // SetTrackStatement