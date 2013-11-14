package com.github.tomap.modeler.model.diagramClass.aclass;

import java.util.LinkedList;
import java.util.List;

import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.model.diagramClass.type.Type;
import com.github.tomap.modeler.model.diagramClass.typedElement.Attribute;
import com.github.tomap.modeler.model.diagramClass.typedElement.Method;

public class A_Class extends Type{
	
	public final static String EMPTY_CLASS = "";
	
	private boolean isFinal;
	private boolean isStatic;
	private boolean isAbstract;
	
	private A_Package aPackage;
	private List<Method> listMethod;
	private List<Attribute> listAttribute;
	
	public A_Class(String name, boolean isFinal, boolean isStatic, boolean isAbstract, A_Package apackage) {
		super(name);
		this.isAbstract = isAbstract;
		this.isFinal = isFinal;
		this.isStatic = isStatic;
		this.listAttribute = new LinkedList<Attribute>();
		this.listMethod = new LinkedList<Method>();
                this.aPackage = apackage;
		
	}
	
	public A_Class() {
		super(EMPTY_CLASS);
		this.aPackage = new A_Package(EMPTY_CLASS);
	}
	
	public void addMethod(Method m){
		listMethod.add(m);
	}
	
	public void removeMethod(Method m){
		listMethod.remove(m);
	}
	
	public void addAttribute(Attribute a){
		listAttribute.add(a);
	}
	
	public void removeAttribute(Attribute a){
		listAttribute.remove(a);
	}
	
	
	
	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public A_Package getaPackage() {
		return aPackage;
	}

	public void setaPackage(A_Package aPackage) {
		this.aPackage = aPackage;
	}

	@Override
	public String toString(){
		if(this.name.equals(EMPTY_CLASS) && this.aPackage.getName().equals(EMPTY_CLASS)){
			return EMPTY_CLASS;
		}
		return this.name+ "("+this.aPackage.getName()+")";
	}
	
	public String display(){
		
		String s = (isAbstract ? "Abstract " : "");
		s+= (isFinal ? "Final " : "");
		s+= (isStatic ? "Static " : "");
		s+= this.name + " {\n";
		
		s+= "\n";
		
		for (Attribute a : listAttribute){
			s+= a.display()+"\n";
		}
		
		s+= "\n";
		
		for (Method m : listMethod){
			s+=m.display()+"\n";
		}
		
		s+="}";
		
		return s;
	}
	
}
