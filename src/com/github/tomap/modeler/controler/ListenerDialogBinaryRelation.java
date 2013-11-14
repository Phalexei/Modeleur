package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.github.tomap.modeler.view.dialog.DialogBinaryRelation;

public class ListenerDialogBinaryRelation implements ActionListener {
	/**
	 * <h4>ListenerDialogBinaryRelation listens the binary relation dialog</h4>
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //
		
	private DialogBinaryRelation dialogBinaryRelation;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ------------------------------------------//

	public ListenerDialogBinaryRelation(DialogBinaryRelation dialogBinaryRelation){
		this.dialogBinaryRelation = dialogBinaryRelation;
		
		this.dialogBinaryRelation.getValid().addActionListener(this);
		this.dialogBinaryRelation.getCancel().addActionListener(this);
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.dialogBinaryRelation.getValid()){
			dialogBinaryRelation.makeRelation();
			
		}else if (e.getSource() == this.dialogBinaryRelation.getCancel()){
			dialogBinaryRelation.dispose();	
		} 

	}

}
