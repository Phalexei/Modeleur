package com.github.tomap.modeler.model.diagramClass.relation;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.excpetion.BadTypeException;
import com.github.tomap.modeler.model.diagramClass.type.Type;

public class Generalization extends Relation{

	public final static String NAME = "Generalization";
	
	private Type from;
	private Type to;
	
	public Generalization(Type type1, Type type2) throws BadTypeException {
		super("extends");
		
		if ((type1 instanceof A_Class && type2 instanceof A_Class) ||
				(type1 instanceof An_Interface && type2 instanceof An_Interface)){
		
			this.from = type1;
			this.to = type2;
		}
		else{
			throw new BadTypeException("both types must be A_Class or An_Interface !");
		}
		
	}
	
	public Generalization() {
		super("extends");
	}
	
	@Override
	public String toString(){
		
		return NAME;
	}
	
	@Override
	public String display(){
		return this.from.getName() + " ---extends---> " + this.to.getName();
	}
	public Type getFrom() {
		return from;
	}

	public void setFrom(Type from) {
		this.from = from;
	}

	public Type getTo() {
		return to;
	}

	public void setTo(Type to) {
		this.to = to;
	}
}
