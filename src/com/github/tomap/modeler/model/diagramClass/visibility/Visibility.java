package com.github.tomap.modeler.model.diagramClass.visibility;

public abstract class Visibility {
	
	private String name;

	public Visibility(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	
	public String display(){
		return name;
	}

}
