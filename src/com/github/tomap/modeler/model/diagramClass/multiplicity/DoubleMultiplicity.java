package com.github.tomap.modeler.model.diagramClass.multiplicity;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;

public class DoubleMultiplicity extends SingleMultiplicity {

	private int maxValue;
	
	public DoubleMultiplicity(int value,int maxvalue, String attributeName, A_Class aClass, Relation relation) {
		super(value, attributeName, aClass, relation);
		this.maxValue = maxvalue;
	}
	
	@Override
	public String toString(){
		return "";
	}
	
	@Override
	public String display(){
		return this.aClass.getName()+
				"("+ this.value + "..." + 
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
		if (value != Multiplicity.NO_VALUE){
			s += value;
			s += "..";
			if (maxValue == Multiplicity.VALUE_MAX){
				s+="*";
			}else if (maxValue != NO_VALUE && maxValue!=VALUE_MAX){
				s+=maxValue;
			}
		}
		
		s+= this.attributeName;
		return s;
	}
}
