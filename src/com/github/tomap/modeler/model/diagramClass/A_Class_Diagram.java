package com.github.tomap.modeler.model.diagramClass;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;

public class A_Class_Diagram {

	private HashMap<String,A_Package> listPackages;
	private List<Relation> listRelations;

	public A_Class_Diagram() {
		super();
		this.listPackages = new HashMap<String, A_Package>();
		this.listRelations = new LinkedList<Relation>();
	}

	public void addPackage(A_Package p){
		this.listPackages.put(p.getName(), p);
	}

	public void removePackage(A_Package p){
		this.listPackages.remove(p.toString());
	}

	public void addRelation (Relation r){
		this.listRelations.add(r);
	}

	public void removeRelation (Relation r){
		this.listRelations.remove(r);
	}



	public HashMap<String, A_Package> getListPackages() {
		return listPackages;
	}

	public void setListPackages(HashMap<String, A_Package> listPackages) {
		this.listPackages = listPackages;
	}

	public List<Relation> getListRelations() {
		return listRelations;
	}

	public void setListRelations(List<Relation> listRelations) {
		this.listRelations = listRelations;
	}

	public String display(){
		String s = "====== CLASS DIAGRAMM ======\n";

		for (Map.Entry entry : listPackages.entrySet()) {
			s+=((A_Package) entry.getValue()).display();
		}

		s+= "\n\n";

		for (Relation r : listRelations){
			s+= r.display()+"\n";
		}

		return s;

	}

}
