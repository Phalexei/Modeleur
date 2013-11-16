package com.github.tomap.modeler.controler;

import com.github.tomap.modeler.model.diagramClass.A_Class_Diagram;

public abstract class DialogController extends DiagramController{

	/**
	 * <h4>DiagramController is a generic class to control dialog components</h4>
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //
	
	
	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ------------------------------------------//

	public DialogController(A_Class_Diagram aclassDiagram){
		super(aclassDiagram);

	}
	
	public DialogController(){
		super();
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //
	
}
