package com.github.tomap.modeler.model.diagramClass.typedElement;

import com.github.tomap.modeler.model.diagramClass.type.Type;

public abstract class TypedElement {

	protected String name;
	protected boolean isFinal;
	protected Type type;
	
	public TypedElement(String name, boolean isFinal, Type type) {
		super();
		this.name = name;
		this.isFinal = isFinal;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	
	
	
}
