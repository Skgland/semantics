/**
 */
package de.cau.cs.kieler.core.kexpressions.keffects.provider;


import de.cau.cs.kieler.core.kexpressions.KExpressionsFactory;

import de.cau.cs.kieler.core.kexpressions.keffects.AssignOperator;
import de.cau.cs.kieler.core.kexpressions.keffects.Assignment;
import de.cau.cs.kieler.core.kexpressions.keffects.KEffectsFactory;
import de.cau.cs.kieler.core.kexpressions.keffects.KEffectsPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link de.cau.cs.kieler.core.kexpressions.keffects.Assignment} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AssignmentItemProvider extends EffectItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AssignmentItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addValuedObjectPropertyDescriptor(object);
            addOperatorPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Valued Object feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addValuedObjectPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Assignment_valuedObject_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Assignment_valuedObject_feature", "_UI_Assignment_type"),
                 KEffectsPackage.Literals.ASSIGNMENT__VALUED_OBJECT,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Operator feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addOperatorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Assignment_operator_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Assignment_operator_feature", "_UI_Assignment_type"),
                 KEffectsPackage.Literals.ASSIGNMENT__OPERATOR,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION);
            childrenFeatures.add(KEffectsPackage.Literals.ASSIGNMENT__INDICES);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns Assignment.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Assignment"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        AssignOperator labelValue = ((Assignment)object).getOperator();
        String label = labelValue == null ? null : labelValue.toString();
        return label == null || label.length() == 0 ?
            getString("_UI_Assignment_type") :
            getString("_UI_Assignment_type") + " " + label;
    }
    

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Assignment.class)) {
            case KEffectsPackage.ASSIGNMENT__OPERATOR:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case KEffectsPackage.ASSIGNMENT__EXPRESSION:
            case KEffectsPackage.ASSIGNMENT__INDICES:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KEffectsFactory.eINSTANCE.createHostcodeEffect()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KEffectsFactory.eINSTANCE.createFunctionCallEffect()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KExpressionsFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KExpressionsFactory.eINSTANCE.createValuedObjectReference()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KExpressionsFactory.eINSTANCE.createValue()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KExpressionsFactory.eINSTANCE.createIntValue()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KExpressionsFactory.eINSTANCE.createFloatValue()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KExpressionsFactory.eINSTANCE.createBoolValue()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KExpressionsFactory.eINSTANCE.createOperatorExpression()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KExpressionsFactory.eINSTANCE.createTextExpression()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KExpressionsFactory.eINSTANCE.createFunctionCall()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION,
                 KExpressionsFactory.eINSTANCE.createStringValue()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KEffectsFactory.eINSTANCE.createHostcodeEffect()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KEffectsFactory.eINSTANCE.createFunctionCallEffect()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KExpressionsFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KExpressionsFactory.eINSTANCE.createValuedObjectReference()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KExpressionsFactory.eINSTANCE.createValue()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KExpressionsFactory.eINSTANCE.createIntValue()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KExpressionsFactory.eINSTANCE.createFloatValue()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KExpressionsFactory.eINSTANCE.createBoolValue()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KExpressionsFactory.eINSTANCE.createOperatorExpression()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KExpressionsFactory.eINSTANCE.createTextExpression()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KExpressionsFactory.eINSTANCE.createFunctionCall()));

        newChildDescriptors.add
            (createChildParameter
                (KEffectsPackage.Literals.ASSIGNMENT__INDICES,
                 KExpressionsFactory.eINSTANCE.createStringValue()));
    }

    /**
     * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify =
            childFeature == KEffectsPackage.Literals.ASSIGNMENT__EXPRESSION ||
            childFeature == KEffectsPackage.Literals.ASSIGNMENT__INDICES;

        if (qualify) {
            return getString
                ("_UI_CreateChild_text2",
                 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}