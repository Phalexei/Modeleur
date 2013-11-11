package com.github.tomap.modeler.model.diagramClass.relation;

public class Agregation extends BinaryRelation {

	public final static String NAME = "Agregation";
	
	public Agregation(String name) {
		super(name, null, null);
	}
	
	public Agregation() {
		super("", null, null);
	}
	
	@Override
	public String toString(){
		return NAME;
	}
	@Override
	public String display(){
		return this.from.display() + "---agregation : "+name+"--->" + this.to.display();
	}

}
