package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.view.dialog.DialogClass;

public class ListenerDialogClass extends DialogController implements ActionListener {

    /**
     * <h4>ListenerDialogClass listens the class dialog</h4>
     *
     * @author Alexis CHRETIENNE
     */
	// ----------------------------------------- //
    // --------------- CONSTANTS --------------- //
    // ----------------------------------------- //
    // ----------------------------------------- //
    // ----------------ATRIBUTES---------------- //
    // ----------------------------------------- //
    private DialogClass dialogClass;

    // ----------------------------------------- //
    // --------------CONSTRUCTOR---------------- //
    // ------------------------------------------//
    public ListenerDialogClass(DialogClass dialogClass) {
    	super(dialogClass.getcGlobal().getContainerTabbedPane().getPanelClass().getDiagram());
        this.dialogClass = dialogClass;
        this.dialogClass.getValid().addActionListener(this);
        this.dialogClass.getCancel().addActionListener(this);
    }

    // ----------------------------------------- //
    // -----------------METHODS----------------- //
    // ----------------------------------------- //
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.dialogClass.getValid()) {
            addClass();
        } else if (e.getSource() == this.dialogClass.getCancel()) {
            dialogClass.resetDialog();
            dialogClass.setVisible(false);
        }

    }

    public void addClass() {
        String packagename = dialogClass.getTextPackageName().getText();
        String classname = dialogClass.getTextClassName().getText();
        boolean isStatic = dialogClass.getCb_static().isSelected();
        boolean isFinal = dialogClass.getCb_final().isSelected();
        boolean isAbstract = dialogClass.getCb_abstract().isSelected();

        // update model
        A_Package p;
        if (aclassDiagram.getListPackages().containsKey(packagename)) {
            p = aclassDiagram.getListPackages().get(packagename);
        } else {
            p = new A_Package(packagename);
            aclassDiagram.addPackage(p);
        }

        A_Class c = new A_Class(classname, isFinal, isStatic, isAbstract, p);
        p.addClass(c);

        dialogClass.getcGlobal().getContainerTabbedPane().getPanelClass().addGraphicalClass(c);

        dialogClass.resetDialog();
        dialogClass.setVisible(false);
    }
}
