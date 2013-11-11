package com.github.tomap.modeler.model.diagramClass.multiplicity;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;

public class NoMultiplicity extends Multiplicity {

	public NoMultiplicity(A_Class aClass, Relation relation) {
		super(aClass, relation);
	}
	
	@Override
	public String toString(){
		return "";
	}
	
	@Override
	public String display(){
		return this.aClass.getName();
	}

}
