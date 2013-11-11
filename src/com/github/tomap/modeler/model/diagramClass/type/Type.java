package com.github.tomap.modeler.model.diagramClass.type;

public abstract class Type {

	protected String name;

	public Type(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String display(){
		return this.name;
	}
	
}
