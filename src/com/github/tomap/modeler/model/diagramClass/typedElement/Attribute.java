package com.github.tomap.modeler.model.diagramClass.typedElement;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.excpetion.BadTypeException;
import com.github.tomap.modeler.model.diagramClass.type.Type;
import com.github.tomap.modeler.model.diagramClass.visibility.Visibility;

public class Attribute extends VisibleTypedElement{

	private boolean isDerivated;
	private Type belongtoType;
	
	public Attribute(String name, boolean isFinal, Type type, boolean isStatic, boolean isDerivated,  Type belongtoType, Visibility visibility) throws BadTypeException {
		super(name, isFinal, type, isStatic, visibility);
		this.isDerivated = isDerivated;
		
		if (belongtoType instanceof A_Class){
			this.belongtoType = belongtoType;
		}else{
			throw new BadTypeException("Type must be A_Class !");
		}
		
	}
	
	public Attribute(String name, boolean isFinal, Type type, boolean isStatic, boolean isDerivated, Visibility visibility) {
		super(name, isFinal, type, isStatic, visibility);
		this.isDerivated = isDerivated;
	}
	
	@Override
	public String toString(){
		return display();
	}
	
	public String display(){
		String s = this.visibility.toString() + " " ;
		s+= (isDerivated ? "Derivated " : "");
		s+= (isFinal ? "Final " : "");
		s+= (isStatic ? "Static " : "");
		s+= this.type.display() + " ";
		s+= this.name + ";";
		
		return s;
	}

	public boolean isDerivated() {
		return isDerivated;
	}

	public void setDerivated(boolean isDerivated) {
		this.isDerivated = isDerivated;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	public Visibility getVisibility(){
		return visibility;
	}
	
	public void setVisibility(Visibility visibility){
		this.visibility = visibility;
	}
	
	public Type getBelongtoType() {
		return belongtoType;
	}

	public void setBelongtoType(Type belongtoType) {
		this.belongtoType = belongtoType;
	}

	
	
	
}
