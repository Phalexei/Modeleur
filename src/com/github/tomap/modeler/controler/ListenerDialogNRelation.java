package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.multiplicity.DoubleMultiplicity;
import com.github.tomap.modeler.model.diagramClass.relation.Association;
import com.github.tomap.modeler.model.diagramClass.relation.N_Relation;
import com.github.tomap.modeler.view.dialog.DialogNrelation;

public class ListenerDialogNRelation extends DialogController implements ActionListener {
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
	private JList<Object> jlistClasses;
	private Object[] arrayClassesUsed;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ------------------------------------------//

	public ListenerDialogNRelation(DialogNrelation dialogNRelation) {
		super(dialogNRelation.getcGlobal().getContainerTabbedPane().getDiagram());
		this.dialogNRelation = dialogNRelation;
		this.jlistClasses = dialogNRelation.getListClasses();
		
		this.dialogNRelation.getValid().addActionListener(this);
		this.dialogNRelation.getCancel().addActionListener(this);

	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.dialogNRelation.getValid()) {
			arrayClassesUsed =  jlistClasses.getSelectedValues();
			
			if(arrayClassesUsed == null || arrayClassesUsed.length == 0){
				dialogNRelation.getAreaError().setText("There is not any selections");
			}else if(arrayClassesUsed.length < 3){
				dialogNRelation.getAreaError().setText("Select a minimum of 3 classes to make an N relation");
			}else{
				
				makeNRelation();
				dialogNRelation.dispose();
			}
			
		} else if (e.getSource() == this.dialogNRelation.getCancel()) {
			dialogNRelation.dispose();
		}

	}
	
	public void makeNRelation(){
		N_Relation rn = new N_Relation("n-ary");
		for (int i = 0; i < arrayClassesUsed.length; i++){
			A_Class a = (A_Class) arrayClassesUsed[i];
			DoubleMultiplicity d = new DoubleMultiplicity(0, 0, "", a, rn);
			rn.addMultiplicity(d);
		}
		
		if(dialogNRelation.getIsAssociative().isSelected()){
			makeAssociationNRelation(rn);
		}else{
			//update model
			aclassDiagram.addRelation(rn);
			//update ui
			dialogNRelation.getcGlobal().getContainerTabbedPane().getPanelClass().addGraphicalRelation(rn);
		}
	}
	
	public void makeAssociationNRelation(N_Relation rn){
		A_Class associative = (A_Class) dialogNRelation.getComboAssociativeWith().getSelectedItem();
		Association ass = new Association(associative, rn);
		// update model
		aclassDiagram.addRelation(ass);
		//update ui
		dialogNRelation.getcGlobal().getContainerTabbedPane().getPanelClass().addGraphicalRelation(ass);
	}

}
