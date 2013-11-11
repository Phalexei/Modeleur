package com.github.tomap.modeler.model.diagramClass.relation;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;

public class Association extends Relation {

	public final static String NAME = "Association";

	private A_Class associativeClass;
	private Relation relation;

	public Association(A_Class associativeClass, Relation relation) {
		super("");
		this.associativeClass = associativeClass;
		this.relation = relation;
	}

	public Association() {
		super("");
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public String display() {
		 String s = this.relation.display();
		 s+= "\n";
		 s+= "associative class : "+associativeClass.getName();

		 return s;
	}

	public A_Class getAssociativeClass() {
		return associativeClass;
	}

	public void setAssociativeClass(A_Class associativeClass) {
		this.associativeClass = associativeClass;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	

	
}
