package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.tomap.modeler.view.dialog.DialogClass;

public class ListenerDialogClass implements ActionListener {
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

	public ListenerDialogClass(DialogClass dialogClass){
		this.dialogClass = dialogClass;
		this.dialogClass.getValid().addActionListener(this);
		this.dialogClass.getCancel().addActionListener(this);
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.dialogClass.getValid()){
			String packagename = dialogClass.getTextPackageName().getText();
			String classname = dialogClass.getTextClassName().getText();
			boolean isStatic = dialogClass.getCb_static().isSelected();
			boolean isFinal = dialogClass.getCb_final().isSelected();
			boolean isAbstract = dialogClass.getCb_abstract().isSelected();

			dialogClass.getcGlobal().getContainerTabbedPane().getPanelClass()
					.addClass(packagename, classname, isAbstract, isFinal,isStatic);

			dialogClass.resetDialog();
			dialogClass.setVisible(false);
			
		}else if (e.getSource() == this.dialogClass.getCancel()){
			dialogClass.resetDialog();
			dialogClass.setVisible(false);
		} 

	}

}
