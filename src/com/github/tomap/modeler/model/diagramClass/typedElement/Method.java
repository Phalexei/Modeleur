package com.github.tomap.modeler.model.diagramClass.typedElement;

import java.util.LinkedList;
import java.util.List;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.exception.BadTypeException;
import com.github.tomap.modeler.model.diagramClass.type.Integer;
import com.github.tomap.modeler.model.diagramClass.type.Type;
import com.github.tomap.modeler.model.diagramClass.visibility.Private;
import com.github.tomap.modeler.model.diagramClass.visibility.Visibility;

public class Method extends VisibleTypedElement{

	private boolean isAbstract;
	private List<Parameter> listParameters;
	private Type belongtoType;
	
	public Method(Visibility v, Type typeReturn, String name,boolean isFinal, boolean isAbstract, Type belongtoType) throws BadTypeException {
		super(name, isFinal, typeReturn, isAbstract, v);
		this.isAbstract = isAbstract;
		this.listParameters = new LinkedList<Parameter>();
		
		// checks if a method belongs to class or interface type.
		if ((belongtoType instanceof A_Class) || (belongtoType instanceof An_Interface)){
			this.belongtoType = belongtoType;
		}else{
			throw new BadTypeException("Type must be An_Interface or A_Class !");
		}
	}
	
	public Method(Visibility v, Type typeReturn, String name,boolean isFinal, boolean isAbstract) {
		super(name, isFinal, typeReturn, isAbstract, v);
		this.isAbstract = isAbstract;
		this.listParameters = new LinkedList<Parameter>();
		
	}
	
	public Method(String name){
		super(name, false, new Integer(), false, new Private());
	}
	
	public void addParameter(Parameter p){
		listParameters.add(p);
	}
	
	public void removeParameter(Parameter p){
		listParameters.remove(p);
	}
	
	
	public Type getBelongtoType() {
		return belongtoType;
	}

	public void setBelongtoType(Type belongtoType) {
		this.belongtoType = belongtoType;
	}

	@Override
	public String toString(){
		return display();
	}
	
	public String display(){
		String s =  this.visibility.display() + " ";
		s+= this.isFinal ? "final " : "";
		s+= this.isAbstract ? "abstract " : "";
		s+=	this.type.display() + " " +
			this.name + " (";
		
		for (int i=0 ; i< listParameters.size(); i++){
			s += listParameters.get(i).display(); 
			if (i != listParameters.size()-1){
				s+=", ";
			}
		}
		
		s = s+= ")";
		
		return s;
	}
	
	
	
}
