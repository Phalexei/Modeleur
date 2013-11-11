package com.github.tomap.modeler.model.diagramClass.typedElement;

import com.github.tomap.modeler.model.diagramClass.type.Type;

public class Parameter extends TypedElement{

	private Method method;
	
	public Parameter(String name, boolean isFinal, Type type, Method method) {
		super(name, isFinal, type);
		this.method = method;
	}
	
	@Override
	public String toString(){
		return display();
	}
	
	public String display(){
		String s = this.isFinal ? "final " : "";
		s+= 	this.type.toString() + " " + this.name;
		
		return s;
	}
	
	

	
	
}
