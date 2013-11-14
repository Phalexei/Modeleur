package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.typedElement.Attribute;
import com.github.tomap.modeler.view.dialog.DialogAttributeClass;

public class ListenerDialogAttribute implements ActionListener {
	/**
	 * <h4>ListenerDialogAttribute listens the attribute dialog</h4>
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //
		
	private DialogAttributeClass dialogAttribute;
	private A_Class aclass;
	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ------------------------------------------//

	public ListenerDialogAttribute(DialogAttributeClass dialogAttribute){
		this.dialogAttribute = dialogAttribute;
		this.aclass = dialogAttribute.getA_class();
		this.dialogAttribute.getValid().addActionListener(this);
		this.dialogAttribute.getCancel().addActionListener(this);
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.dialogAttribute.getValid()){
			
			// list attribute
			for (int i = 0; i<dialogAttribute.getAttributeModel().getRowCount(); i++){
        		for (int j = 0; j<dialogAttribute.getAttributeModel().getColumnCount(); j++){
        			
        			Attribute a = (Attribute)dialogAttribute.getAttributeModel().getValueAt(i, j);
        			a.setBelongtoType(aclass);
        			//update model
        			aclass.addAttribute(a);
        			//update ui
        			dialogAttribute.getModelJlist().addElement(a);
        			
        		}
        	}
			
			dialogAttribute.getRemoveButton().setEnabled(true);
			dialogAttribute.setVisible(false);
			
		}else if (e.getSource() == this.dialogAttribute.getCancel()){
			dialogAttribute.setVisible(false);
		} 

	}

}
