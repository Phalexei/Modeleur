package com.github.tomap.modeler.model.diagramClass.relation;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;

public class Implementation extends Relation{
	
	public final static String NAME = "Implementation";
	
	private A_Class aClass;
	private An_Interface anInterface;
	
	public Implementation(A_Class aClass, An_Interface anInterface) {
		super("implements");
		this.aClass = aClass;
		this.anInterface = anInterface;
	}
	
	public Implementation() {
		super("implements");
		
	}
	
	@Override
	public String toString(){
		return NAME;
	}
	
	@Override
	public String display(){
		return this.aClass.getName() + " ---implements---> " + this.anInterface.getName();
	}

	public A_Class getaClass() {
		return aClass;
	}

	public void setaClass(A_Class aClass) {
		this.aClass = aClass;
	}

	public An_Interface getAnInterface() {
		return anInterface;
	}

	public void setAnInterface(An_Interface anInterface) {
		this.anInterface = anInterface;
	}
	
	
	
}
