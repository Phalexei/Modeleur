package com.github.tomap.modeler.model.diagramClass.multiplicity;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;

public class DoubleMultiplicity extends Multiplicity {

	private int maxValue;
	private int minvalue;
	private String attributeName;
	
	public DoubleMultiplicity(int value,int maxvalue, String attributeName, A_Class aClass, Relation relation) {
		super(aClass, relation);
		this.maxValue = maxvalue;
		this.minvalue = value;
		this.attributeName = attributeName;
	}
	
	@Override
	public String toString(){
		return "";
	}
	
	@Override
	public String display(){
		return this.aClass.getName()+
				"("+ this.minvalue + "..." + 
				this.maxValue + " " + 
				this.attributeName+")";
	}
	
	
	
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public String getMultiplicities(){
		String s = "";
		if(minvalue == Multiplicity.VALUE_MAX){	
			s += "*";
		}else if (minvalue != Multiplicity.NO_VALUE){
			s += minvalue;
			if (maxValue == Multiplicity.VALUE_MAX){
				s += "..";
				s+="*";
			}else if (maxValue != NO_VALUE && maxValue!=VALUE_MAX){
				s += "..";
				s+=maxValue;
			}
		}
		
		s+= this.attributeName;
		return s;
	}
}
