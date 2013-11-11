package com.github.tomap.modeler.model.diagramClass.type;

public abstract class TypeBase extends Type {

	
	private int byteSize;
	
	public TypeBase(String name, int byteSize) {
		super(name);
		this.byteSize = byteSize;
	}

}
