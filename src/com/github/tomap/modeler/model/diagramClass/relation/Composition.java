package com.github.tomap.modeler.model.diagramClass.relation;

public class Composition extends BinaryRelation {

	public static final  String NAME = "Composition";
	
	public Composition(String name) {
		super(name, null, null);
	}
	
	public Composition() {
		super("", null, null);
	}
	
	
	@Override
	public String toString(){
		return NAME;
	}
	
	@Override
	public String display(){
		return this.from.display() + "---composition : "+name+"--->" + this.to.display();
	}
	
}
