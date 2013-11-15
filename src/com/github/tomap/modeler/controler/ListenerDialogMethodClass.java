package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.typedElement.Method;
import com.github.tomap.modeler.model.diagramClass.typedElement.Parameter;
import com.github.tomap.modeler.model.diagramClass.visibility.Visibility;
import com.github.tomap.modeler.view.dialog.DialogMethodClass;

public class ListenerDialogMethodClass implements ActionListener {

    /**
     * <h4>ListenerDialogMethodClass listens the attribute dialog for a
     * class</h4>
     *
     * @author Alexis CHRETIENNE
     */
	// ----------------------------------------- //
    // --------------- CONSTANTS --------------- //
    // ----------------------------------------- //
    // ----------------------------------------- //
    // ----------------ATRIBUTES---------------- //
    // ----------------------------------------- //
    private DialogMethodClass dialogMethodClass;
    private A_Class aclass;

    // ----------------------------------------- //
    // --------------CONSTRUCTOR---------------- //
    // ------------------------------------------//
    public ListenerDialogMethodClass(DialogMethodClass dialogMethodClass) {
        this.dialogMethodClass = dialogMethodClass;
        this.aclass = dialogMethodClass.getA_class();
        this.dialogMethodClass.getValid().addActionListener(this);
        this.dialogMethodClass.getCancel().addActionListener(this);
    }

    // ----------------------------------------- //
    // -----------------METHODS----------------- //
    // ----------------------------------------- //
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.dialogMethodClass.getValid()) {
            addMethod();
        } else if (e.getSource() == this.dialogMethodClass.getCancel()) {
            dialogMethodClass.setVisible(false);
        }

    }

    public void addMethod() {
        Visibility v = (Visibility) dialogMethodClass.getComboVisibility().getSelectedItem();
        com.github.tomap.modeler.model.diagramClass.type.Type returnType = (com.github.tomap.modeler.model.diagramClass.type.Type) dialogMethodClass.getComboReturnType()
                .getSelectedItem();
        String name = dialogMethodClass.getTextMethodName().getText();
        boolean isFinal = dialogMethodClass.getFinalButton().isSelected();
        boolean isAbstract = dialogMethodClass.getAbstractButton().isSelected();

        Method m = new Method(v, returnType, name, isFinal, isAbstract);

        for (int i = 0; i < dialogMethodClass.getParameterModel().getRowCount(); i++) {
            for (int j = 0; j < dialogMethodClass.getParameterModel().getColumnCount(); j++) {

                Parameter p = (Parameter) dialogMethodClass.getParameterModel().getValueAt(i, j);
                m.addParameter(p);

            }
        }
        //update model
        m.setBelongtoType(aclass);
        aclass.addMethod(m);
        //update ui
        dialogMethodClass.getModelJlist().addElement(m);
        dialogMethodClass.getRemoveButton().setEnabled(true);
        dialogMethodClass.setVisible(false);
    }
}
