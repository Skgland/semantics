/**
 * generated by Xtext 2.10.0
 */
package de.cau.cs.kieler.railSL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contact Wait Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.railSL.ContactWaitStatement#getEvent <em>Event</em>}</li>
 *   <li>{@link de.cau.cs.kieler.railSL.ContactWaitStatement#getContactIndex <em>Contact Index</em>}</li>
 *   <li>{@link de.cau.cs.kieler.railSL.ContactWaitStatement#getSegName <em>Seg Name</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.railSL.RailSLPackage#getContactWaitStatement()
 * @model
 * @generated
 */
public interface ContactWaitStatement extends WaitStatement
{
  /**
   * Returns the value of the '<em><b>Event</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Event</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Event</em>' attribute.
   * @see #setEvent(String)
   * @see de.cau.cs.kieler.railSL.RailSLPackage#getContactWaitStatement_Event()
   * @model
   * @generated
   */
  String getEvent();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.railSL.ContactWaitStatement#getEvent <em>Event</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Event</em>' attribute.
   * @see #getEvent()
   * @generated
   */
  void setEvent(String value);

  /**
   * Returns the value of the '<em><b>Contact Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contact Index</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contact Index</em>' attribute.
   * @see #setContactIndex(String)
   * @see de.cau.cs.kieler.railSL.RailSLPackage#getContactWaitStatement_ContactIndex()
   * @model
   * @generated
   */
  String getContactIndex();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.railSL.ContactWaitStatement#getContactIndex <em>Contact Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Contact Index</em>' attribute.
   * @see #getContactIndex()
   * @generated
   */
  void setContactIndex(String value);

  /**
   * Returns the value of the '<em><b>Seg Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Seg Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Seg Name</em>' attribute.
   * @see #setSegName(String)
   * @see de.cau.cs.kieler.railSL.RailSLPackage#getContactWaitStatement_SegName()
   * @model
   * @generated
   */
  String getSegName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.railSL.ContactWaitStatement#getSegName <em>Seg Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Seg Name</em>' attribute.
   * @see #getSegName()
   * @generated
   */
  void setSegName(String value);

} // ContactWaitStatement