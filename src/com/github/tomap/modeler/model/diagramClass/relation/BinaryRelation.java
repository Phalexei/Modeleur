package com.github.tomap.modeler.model.diagramClass.relation;

import com.github.tomap.modeler.model.diagramClass.multiplicity.Multiplicity;

public abstract class BinaryRelation extends Relation {

	protected Multiplicity from;
	protected Multiplicity to;
	
	public BinaryRelation(String name, Multiplicity from, Multiplicity to) {
		super(name);
		this.from = from;
		this.to = to;
	}
	
	public void updateMultiplicities(Multiplicity from, Multiplicity to){
		this.from = from;
		this.to = to;
	}
	
	public Multiplicity getMultiplicityFrom(){
		return this.from;
	}
	
	public Multiplicity getMultiplicityTo(){
		return this.to;
	}
}
