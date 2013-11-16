package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.view.dialog.DialogInterface;

public class ListenerDialogInterface extends DialogController implements ActionListener {

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
    
    // ----------------------------------------- //
    // --------------CONSTRUCTOR---------------- //
    // ------------------------------------------//
    public ListenerDialogInterface(DialogInterface dialogInterface) {
    	super(dialogInterface.getcGlobal().getContainerTabbedPane().getPanelClass().getDiagram());
        this.dialogInterface = dialogInterface;
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
        if (aclassDiagram.getListPackages().containsKey(packagename)) {
            p = aclassDiagram.getListPackages().get(packagename);
        } else {
            p = new A_Package(packagename);
            aclassDiagram.addPackage(p);
        }

        An_Interface i = new An_Interface(interfacename, p);

        p.addInterface(i);

        dialogInterface.getcGlobal().getContainerTabbedPane().getPanelClass()
                .addGraphicalInterface(i);

        dialogInterface.resetDialog();
        dialogInterface.setVisible(false);
    }

}
