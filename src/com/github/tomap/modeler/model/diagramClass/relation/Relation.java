package com.github.tomap.modeler.model.diagramClass.relation;

public abstract class Relation {

	protected String name;
	
	public Relation(String name) {
		super();
		this.name = name;
	}

	
	public String display() {
		return "hello";
	}
	
	public String getName(){
		return this.name;
	}
	
}
