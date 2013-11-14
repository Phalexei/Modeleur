package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.tomap.modeler.view.dialog.DialogBinaryRelation;
import com.github.tomap.modeler.view.dialog.DialogNrelation;

public class ListenerDialogNRelation implements ActionListener {
	/**
	 * <h4>ListenerDialogNRelation listens the N relation dialog</h4>
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //
		
	private DialogNrelation dialogNRelation;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ------------------------------------------//

	public ListenerDialogNRelation(DialogNrelation dialogNRelation){
		this.dialogNRelation = dialogNRelation;
		
		//this.dialogBinaryRelation.getValid().addActionListener(this);
		//this.dialogBinaryRelation.getCancel().addActionListener(this);
		
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		if (e.getSource() == this.dialogBinaryRelation.getValid()){
			
			
		}else if (e.getSource() == this.dialogBinaryRelation.getCancel()){
			
		} 
		*/

		// this.diagram.addRelation(r);
	}

}
