package de.cau.cs.kieler.synccharts.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.synccharts.custom.ListCompartmentLayout;
import de.cau.cs.kieler.synccharts.diagram.edit.policies.StateInnerActionCompartment2CanonicalEditPolicy;
import de.cau.cs.kieler.synccharts.diagram.edit.policies.StateInnerActionCompartment2ItemSemanticEditPolicy;
import de.cau.cs.kieler.synccharts.diagram.part.Messages;

/**
 * @generated
 */
public class StateInnerActionCompartment2EditPart extends ShapeCompartmentEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 7076;

    /**
     * @generated
     */
    public StateInnerActionCompartment2EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    public String getCompartmentName() {
        return Messages.StateInnerActionCompartment2EditPart_title;
    }

    /**
     * @generated
     */
    public IFigure createFigure() {
        ResizableCompartmentFigure result = (ResizableCompartmentFigure) super.createFigure();
        result.setTitleVisibility(false);

        Object border = result.getBorder();
        if (border instanceof LineBorder) {
            LineBorder lineBorder = ((LineBorder) border);
            lineBorder.setWidth(2);
            lineBorder.setColor(ColorConstants.black);
        }
        result.setBorder(null);

        result.getContentPane().setLayoutManager(new ListCompartmentLayout());

        WrappingLabel title = new WrappingLabel();
        title.setText(result.getCompartmentTitle() + " ");
        title.setForegroundColor(ColorConstants.black);
        result.getContentPane().add(title, 0);

        return result;
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ResizableCompartmentEditPolicy());
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new StateInnerActionCompartment2ItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
                new StateInnerActionCompartment2CanonicalEditPolicy());
    }

    /**
     * @generated
     */
    protected void setRatio(Double ratio) {
        if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
            super.setRatio(ratio);
        }
    }

}