package com.github.tomap.modeler.model.diagramClass.relation;

import java.util.LinkedList;
import java.util.List;

import com.github.tomap.modeler.model.diagramClass.multiplicity.Multiplicity;

public class N_Relation extends Relation {

	public final static String NAME = "N-relation";
	
	private List<Multiplicity> listMultiplicity;
	
	public N_Relation(String name) {
		super(name);
		this.listMultiplicity = new LinkedList<Multiplicity>();
	}
	
	public N_Relation() {
		super("");
	}
	
	public void addMultiplicity(Multiplicity m){
		listMultiplicity.add(m);
	}
	
	public void RemoveMultiplicity(Multiplicity m){
		listMultiplicity.remove(m);
	}
	
	@Override
	public String toString(){
		return NAME;
	}
	
	@Override
	public String display(){
		String s = "Association : ("+name+ ") between classes : \n";
		for (Multiplicity m : listMultiplicity){
			s+= m.display()+"\n";
		}
		
		return s;
	}
	
}
