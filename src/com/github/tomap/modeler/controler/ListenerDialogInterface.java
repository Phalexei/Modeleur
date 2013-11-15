package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.tomap.modeler.model.diagramClass.A_Class_Diagram;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.view.dialog.DialogInterface;

public class ListenerDialogInterface implements ActionListener {

    /**
     * <h4>ListenerDialogInterface listens the interface dialog</h4>
     *
     * @author Alexis CHRETIENNE
     */
	// ----------------------------------------- //
    // --------------- CONSTANTS --------------- //
    // ----------------------------------------- //
    // ----------------------------------------- //
    // ----------------ATRIBUTES---------------- //
    // ----------------------------------------- //
    private DialogInterface dialogInterface;
    private A_Class_Diagram classDiagram;

    // ----------------------------------------- //
    // --------------CONSTRUCTOR---------------- //
    // ------------------------------------------//
    public ListenerDialogInterface(DialogInterface dialogInterface) {
        this.dialogInterface = dialogInterface;
        this.classDiagram = dialogInterface.getcGlobal().getContainerTabbedPane().getPanelClass().getDiagram();
        this.dialogInterface.getValid().addActionListener(this);
        this.dialogInterface.getCancel().addActionListener(this);
    }

    // ----------------------------------------- //
    // -----------------METHODS----------------- //
    // ----------------------------------------- //
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.dialogInterface.getValid()) {
            addInterface();
        } else if (e.getSource() == this.dialogInterface.getCancel()) {
            dialogInterface.resetDialog();
            dialogInterface.setVisible(false);
        }

    }

    public void addInterface() {
        String packagename = dialogInterface.getTextPackageName().getText();
        String interfacename = dialogInterface.getTextInterfaceName().getText();

        // update model
        A_Package p;
        if (classDiagram.getListPackages().containsKey(packagename)) {
            p = classDiagram.getListPackages().get(packagename);
        } else {
            p = new A_Package(packagename);
            classDiagram.addPackage(p);
        }

        An_Interface i = new An_Interface(interfacename, p);

        p.addInterface(i);

        dialogInterface.getcGlobal().getContainerTabbedPane().getPanelClass()
                .addGraphicalInterface(i);

        dialogInterface.resetDialog();
        dialogInterface.setVisible(false);
    }

}
