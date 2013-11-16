package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.exception.BadTypeException;
import com.github.tomap.modeler.model.diagramClass.multiplicity.DoubleMultiplicity;
import com.github.tomap.modeler.model.diagramClass.multiplicity.Multiplicity;
import com.github.tomap.modeler.model.diagramClass.relation.Agregation;
import com.github.tomap.modeler.model.diagramClass.relation.Association;
import com.github.tomap.modeler.model.diagramClass.relation.BinaryRelation;
import com.github.tomap.modeler.model.diagramClass.relation.Composition;
import com.github.tomap.modeler.model.diagramClass.relation.Generalization;
import com.github.tomap.modeler.model.diagramClass.relation.Implementation;
import com.github.tomap.modeler.model.diagramClass.relation.SimpleRelation;
import com.github.tomap.modeler.view.dialog.DialogBinaryRelation;

public class ListenerDialogBinaryRelation extends DialogController implements ActionListener {
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
		super(dialogBinaryRelation.getcGlobal().getContainerTabbedPane().getPanelClass().getDiagram());
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
			makeRelation();
		}else if (e.getSource() == this.dialogBinaryRelation.getCancel()){
			dialogBinaryRelation.dispose();	
		} 

	}
	
	public void makeRelation() {
		int index = dialogBinaryRelation.getComboTypeRelation().getSelectedIndex();
		switch(index){
		case 0: makeSimpleRelation(); break;
		case 1: makeComposition(); break;
		case 2: makeAgregation(); break;
		case 3: makeGeneralization(); break;
		case 4:makeImplementation(); break;
		}
	}

	private void makeImplementation() {
		
		try{
			A_Class c = (A_Class)dialogBinaryRelation.getComboTypeFrom().getSelectedItem();
			An_Interface i = (An_Interface)dialogBinaryRelation.getComboTypeTo().getSelectedItem();
			
			Implementation im = new Implementation(c,i);
			//update model 
			aclassDiagram.addRelation(im);
			//update ui
			dialogBinaryRelation.getcGlobal().getContainerTabbedPane().getPanelClass().addGraphicalRelation(im);
			dialogBinaryRelation.dispose();	
		}catch(ClassCastException e){
			dialogBinaryRelation.getAreaError().setText(e.getMessage());
		}	
	}

	private void makeGeneralization() {
		
		try {
			Generalization g = new Generalization((com.github.tomap.modeler.model.diagramClass.type.Type)dialogBinaryRelation.getComboTypeFrom()
					.getSelectedItem(), 
					(com.github.tomap.modeler.model.diagramClass.type.Type)dialogBinaryRelation.getComboTypeTo().getSelectedItem());
			//update model 
			aclassDiagram.addRelation(g);
			//update ui
			dialogBinaryRelation.getcGlobal().getContainerTabbedPane().getPanelClass().addGraphicalRelation(g);
			dialogBinaryRelation.dispose();	
		} catch (BadTypeException e) {
			dialogBinaryRelation.getAreaError().setText(e.getMessage());
		}
	}
	
	private void makeAgregation() {
		
		Agregation a = new Agregation(dialogBinaryRelation.getRelationName().getText());
		makeBinaryRelation(a);
		
		
	}
	
	private void makeComposition() {
		Composition c = new Composition(dialogBinaryRelation.getRelationName().getText());
		makeBinaryRelation(c);
		
	}

	private void makeSimpleRelation() {
		SimpleRelation s = new SimpleRelation(dialogBinaryRelation.getRelationName().getText());
		makeBinaryRelation(s);
	}
	
	private void makeBinaryRelation(BinaryRelation r){
		try{
			int valminfrom = getFirstMultiplicity(dialogBinaryRelation.getMultFromMin().getText()); 
			int valmaxfrom = getSecondMultiplicity(dialogBinaryRelation.getMultFromMax().getText());
			
			DoubleMultiplicity from = new DoubleMultiplicity(
					valminfrom,
					valmaxfrom,
					dialogBinaryRelation.getNameAttributeFrom().getText(),
					(A_Class)dialogBinaryRelation.getComboTypeFrom().getSelectedItem(),
					r);
		
			
			int valminto = getFirstMultiplicity(dialogBinaryRelation.getMulttoMin().getText()); 
			int valmaxto = getSecondMultiplicity(dialogBinaryRelation.getMulttoMax().getText());
			DoubleMultiplicity to = new DoubleMultiplicity(
					valminto, 
					valmaxto,
					dialogBinaryRelation.getNameAttributeTo().getText(),
					(A_Class)dialogBinaryRelation.getComboTypeTo().getSelectedItem(),
					r);
			
			r.updateMultiplicities(from, to);
			
			
			if(dialogBinaryRelation.getIsAssociative().isSelected()){
				A_Class associative = (A_Class) dialogBinaryRelation.getComboAssociativeWith().getSelectedItem();
				Association ass = new Association(associative, r);
				//update model 
				aclassDiagram.addRelation(ass);
				//update ui
				dialogBinaryRelation.getcGlobal().getContainerTabbedPane().getPanelClass().addGraphicalRelation(ass);
			}else{
				//update model 
				aclassDiagram.addRelation(r);
				//update ui
				dialogBinaryRelation.getcGlobal().getContainerTabbedPane().getPanelClass().addGraphicalRelation(r);
			}
			
			dialogBinaryRelation.dispose();
		}catch(Exception e){
			dialogBinaryRelation.getAreaError().setText(e.getMessage());
		}
	}
		
	private int getFirstMultiplicity(String val){
		int valMin = Multiplicity.NO_VALUE;
		
		if (val.equals("*")){
			valMin = Multiplicity.VALUE_MAX;
		}
		else{
			valMin = Integer.parseInt(val);
		}
		
		return valMin;
	}
	
	private int getSecondMultiplicity(String val){
		int valMax = Multiplicity.NO_VALUE;
		
		if (!val.isEmpty()){
			valMax = getFirstMultiplicity(val);
		}
		
		return valMax;
	}


}
