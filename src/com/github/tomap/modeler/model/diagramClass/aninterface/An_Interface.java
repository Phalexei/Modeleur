package com.github.tomap.modeler.model.diagramClass.aninterface;

import java.util.LinkedList;
import java.util.List;

import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.model.diagramClass.type.Type;
import com.github.tomap.modeler.model.diagramClass.typedElement.Method;

public class An_Interface extends Type{

	private A_Package aPackage;
	private List<Method> listMethod;

	public An_Interface(String name, A_Package a_Package) {
		super(name);
		this.aPackage = a_Package;
		this.listMethod = new LinkedList<Method>();
	}

	public void addMethod(Method m){
		listMethod.add(m);
	}

	public void removeMethod(Method m){
		listMethod.remove(m);
	}

	@Override
	public String toString(){
		return this.name+ "("+this.aPackage.getName()+")";
	}

	public String display(){
		String s= this.name + " {\n";

		s+= "\n";

		for (Method m : listMethod){
			s+=m.display()+"\n";
		}

		s+="}";

		return s;
	}

	public void setaPackage(A_Package p) {
		this.aPackage = p;
	}

	public A_Package getaPackage() {
		return aPackage;
	}

}
