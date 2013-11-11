package com.github.tomap.modeler.model.diagramClass.multiplicity;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;

public abstract class Multiplicity {
	
	public static final int NO_VALUE = -1;
	public static final int VALUE_MAX = -2;
	
	protected A_Class aClass;
	protected Relation relation;
	
	public Multiplicity(A_Class aClass, Relation relation) {
		super();
		this.aClass = aClass;
		this.relation = relation;
	}

	public String display() {
		// TODO Auto-generated method stub
		return "";
	}
	
	public A_Class getaClass() {
		return aClass;
	}

	public void setaClass(A_Class aClass) {
		this.aClass = aClass;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	public String getMultiplicities(){
		return "";
	}
	
	
}
