/**
 */
package de.cau.cs.kieler.scl.scl.impl;

import de.cau.cs.kieler.scl.scl.SclPackage;
import de.cau.cs.kieler.scl.scl.Statement;
import de.cau.cs.kieler.scl.scl.StatementScope;
import de.cau.cs.kieler.scl.scl.StatementSequence;
import de.cau.cs.kieler.scl.scl.VariableDefinition;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Statement Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.scl.scl.impl.StatementScopeImpl#getStatements <em>Statements</em>}</li>
 *   <li>{@link de.cau.cs.kieler.scl.scl.impl.StatementScopeImpl#getDefinitions <em>Definitions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StatementScopeImpl extends InstructionImpl implements StatementScope
{
  /**
   * The cached value of the '{@link #getStatements() <em>Statements</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatements()
   * @generated
   * @ordered
   */
  protected EList<Statement> statements;

  /**
   * The cached value of the '{@link #getDefinitions() <em>Definitions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefinitions()
   * @generated
   * @ordered
   */
  protected EList<VariableDefinition> definitions;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StatementScopeImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SclPackage.Literals.STATEMENT_SCOPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Statement> getStatements()
  {
    if (statements == null)
    {
      statements = new EObjectContainmentEList<Statement>(Statement.class, this, SclPackage.STATEMENT_SCOPE__STATEMENTS);
    }
    return statements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<VariableDefinition> getDefinitions()
  {
    if (definitions == null)
    {
      definitions = new EObjectContainmentEList<VariableDefinition>(VariableDefinition.class, this, SclPackage.STATEMENT_SCOPE__DEFINITIONS);
    }
    return definitions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SclPackage.STATEMENT_SCOPE__STATEMENTS:
        return ((InternalEList<?>)getStatements()).basicRemove(otherEnd, msgs);
      case SclPackage.STATEMENT_SCOPE__DEFINITIONS:
        return ((InternalEList<?>)getDefinitions()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SclPackage.STATEMENT_SCOPE__STATEMENTS:
        return getStatements();
      case SclPackage.STATEMENT_SCOPE__DEFINITIONS:
        return getDefinitions();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SclPackage.STATEMENT_SCOPE__STATEMENTS:
        getStatements().clear();
        getStatements().addAll((Collection<? extends Statement>)newValue);
        return;
      case SclPackage.STATEMENT_SCOPE__DEFINITIONS:
        getDefinitions().clear();
        getDefinitions().addAll((Collection<? extends VariableDefinition>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SclPackage.STATEMENT_SCOPE__STATEMENTS:
        getStatements().clear();
        return;
      case SclPackage.STATEMENT_SCOPE__DEFINITIONS:
        getDefinitions().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SclPackage.STATEMENT_SCOPE__STATEMENTS:
        return statements != null && !statements.isEmpty();
      case SclPackage.STATEMENT_SCOPE__DEFINITIONS:
        return definitions != null && !definitions.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == StatementSequence.class)
    {
      switch (derivedFeatureID)
      {
        case SclPackage.STATEMENT_SCOPE__STATEMENTS: return SclPackage.STATEMENT_SEQUENCE__STATEMENTS;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == StatementSequence.class)
    {
      switch (baseFeatureID)
      {
        case SclPackage.STATEMENT_SEQUENCE__STATEMENTS: return SclPackage.STATEMENT_SCOPE__STATEMENTS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

} //StatementScopeImpl