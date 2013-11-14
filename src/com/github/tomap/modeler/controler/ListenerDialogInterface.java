package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ------------------------------------------//

	public ListenerDialogInterface(DialogInterface dialogInterface){
		this.dialogInterface = dialogInterface;
		this.dialogInterface.getValid().addActionListener(this);
		this.dialogInterface.getCancel().addActionListener(this);
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.dialogInterface.getValid()){
			String packagename = dialogInterface.getTextPackageName().getText();
			String classname = dialogInterface.getTextInterfaceName().getText();

			dialogInterface.getcGlobal().getContainerTabbedPane().getPanelClass()
					.addInterface(packagename, classname);

			dialogInterface.resetDialog();
			dialogInterface.setVisible(false);

		}else if (e.getSource() == this.dialogInterface.getCancel()){
			dialogInterface.resetDialog();
			dialogInterface.setVisible(false);
		} 

	}

}
