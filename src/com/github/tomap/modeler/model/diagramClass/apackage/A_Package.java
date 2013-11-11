package com.github.tomap.modeler.model.diagramClass.apackage;

import java.util.LinkedList;
import java.util.List;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;


public class A_Package {
	
	private String name;
	private List<An_Interface> listInterfaces;
	private List<A_Class> listClasses;

	public A_Package(String name) {
		super();
		this.name = name;
		this.listInterfaces = new LinkedList<An_Interface>();
		this.listClasses = new LinkedList<A_Class>();
	}
	
	public void addClass(A_Class aClass){
		listClasses.add(aClass);
	}
	
	public void removeClass (A_Class aClass){
		listClasses.remove(aClass);
	}
	
	public void addInterface(An_Interface anInterface){
		listInterfaces.add(anInterface);
	}
	
	public void removeInterface (An_Interface anInterface){
		listInterfaces.remove(anInterface);
	}
	
	

	public List<An_Interface> getListInterfaces() {
		return listInterfaces;
	}

	public List<A_Class> getListClass() {
		return listClasses;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString(){
		return this.name;
	}
	
	public String display(){
		String s = "Package : "+this.name + " [\n";
		
		for (An_Interface i : listInterfaces){
			s+= i.display()+"\n\n";
		}
		
		for (A_Class c : listClasses){
			s+= c.display()+"\n\n";
		}
		
		s+= "]";
		
		
		return s;
	}
}
