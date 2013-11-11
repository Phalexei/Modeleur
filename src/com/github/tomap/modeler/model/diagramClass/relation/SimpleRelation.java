package com.github.tomap.modeler.model.diagramClass.relation;

import com.github.tomap.modeler.model.diagramClass.multiplicity.Multiplicity;

public class SimpleRelation extends BinaryRelation {

	public final static String NAME = "Simple relation";
	
	public SimpleRelation(String name) {
		super(name,null, null);
	}
	
	public SimpleRelation() {
		super("",null, null);
	}
	

	@Override
	public String toString(){
		return NAME;
	}
	
	@Override
	public String display(){
		return this.from.display() + "---"+name+"---" + this.to.display();
	}
	
	
	
	
}
