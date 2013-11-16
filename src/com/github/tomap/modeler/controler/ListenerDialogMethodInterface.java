package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.typedElement.Method;
import com.github.tomap.modeler.model.diagramClass.typedElement.Parameter;
import com.github.tomap.modeler.model.diagramClass.visibility.Visibility;
import com.github.tomap.modeler.view.dialog.DialogMethodInterface;

public class ListenerDialogMethodInterface extends DialogController implements ActionListener {

    /**
     * <h4>ListenerDialogMethodInterface listens the attribute dialog for an
     * interface</h4>
     *
     * @author Alexis CHRETIENNE
     */
	// ----------------------------------------- //
    // --------------- CONSTANTS --------------- //
    // ----------------------------------------- //
    // ----------------------------------------- //
    // ----------------ATRIBUTES---------------- //
    // ----------------------------------------- //
    private DialogMethodInterface dialogMethodInterface;
    private An_Interface anInterface;
    // ----------------------------------------- //
    // --------------CONSTRUCTOR---------------- //
    // ------------------------------------------//

    public ListenerDialogMethodInterface(DialogMethodInterface dialogMethodInterface) {
    	super();
        this.dialogMethodInterface = dialogMethodInterface;
        this.anInterface = dialogMethodInterface.getAnInterface();
        this.dialogMethodInterface.getValid().addActionListener(this);
        this.dialogMethodInterface.getCancel().addActionListener(this);
    }

    // ----------------------------------------- //
    // -----------------METHODS----------------- //
    // ----------------------------------------- //
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.dialogMethodInterface.getValid()) {
            addMethod();
        } else if (e.getSource() == this.dialogMethodInterface.getCancel()) {
            dialogMethodInterface.setVisible(false);
        }

    }

    public void addMethod() {
        Visibility v = (Visibility) dialogMethodInterface.getComboVisibility().getSelectedItem();
        com.github.tomap.modeler.model.diagramClass.type.Type returnType = (com.github.tomap.modeler.model.diagramClass.type.Type) dialogMethodInterface.getComboReturnType().getSelectedItem();
        String name = dialogMethodInterface.getTextMethodName().getText();
        boolean isFinal = dialogMethodInterface.getFinalButton().isSelected();
        boolean isAbstract = dialogMethodInterface.getAbstractButton().isSelected();

        Method m = new Method(v, returnType, name, isFinal, isAbstract);

        for (int i = 0; i < dialogMethodInterface.getParameterModel().getRowCount(); i++) {
            for (int j = 0; j < dialogMethodInterface.getParameterModel().getColumnCount(); j++) {

                Parameter p = (Parameter) dialogMethodInterface.getParameterModel().getValueAt(i,
                        j);
                m.addParameter(p);

            }
        }
        //update model
        m.setBelongtoType(anInterface);
        anInterface.addMethod(m);
        //update ui
        dialogMethodInterface.getModelJlist().addElement(m);
        dialogMethodInterface.getRemoveButton().setEnabled(true);
        dialogMethodInterface.setVisible(false);
    }

}
