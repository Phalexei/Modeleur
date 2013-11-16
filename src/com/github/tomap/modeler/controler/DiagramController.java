package com.github.tomap.modeler.controler;

import com.github.tomap.modeler.model.diagramClass.A_Class_Diagram;

public abstract class DiagramController {

	/**
	 * <h4>Controller is a generic class to control ui/model components</h4>
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //

	protected A_Class_Diagram aclassDiagram;
	
	
	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ------------------------------------------//

	public DiagramController(A_Class_Diagram aclassDiagram){
		super();
		this.aclassDiagram = aclassDiagram;
	}
	
	public DiagramController(){
		super();
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

}
