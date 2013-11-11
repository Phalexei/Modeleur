package com.github.tomap.modeler.model.diagramClass.multiplicity;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;

public class SingleMultiplicity extends Multiplicity {

	protected int value;
	protected String attributeName;
	
	public SingleMultiplicity(int value2, String attributeName,A_Class aClass, Relation relation) {
		super(aClass, relation);
		this.value = value2;
		this.attributeName = attributeName;
	}
	
	@Override
	public String toString(){
		return  "";
	}
	
	public String Display(){
		return  this.aClass.getName()+ "("+value + " "+attributeName+")";
	}
}
