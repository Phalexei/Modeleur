package com.github.tomap.modeler.model.diagramClass.test;

import com.github.tomap.modeler.model.diagramClass.A_Class_Diagram;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.model.diagramClass.excpetion.BadTypeException;
import com.github.tomap.modeler.model.diagramClass.multiplicity.DoubleMultiplicity;
import com.github.tomap.modeler.model.diagramClass.multiplicity.NoMultiplicity;
import com.github.tomap.modeler.model.diagramClass.multiplicity.SingleMultiplicity;
import com.github.tomap.modeler.model.diagramClass.relation.Agregation;
import com.github.tomap.modeler.model.diagramClass.relation.Association;
import com.github.tomap.modeler.model.diagramClass.relation.Composition;
import com.github.tomap.modeler.model.diagramClass.relation.Generalization;
import com.github.tomap.modeler.model.diagramClass.relation.Implementation;
import com.github.tomap.modeler.model.diagramClass.relation.N_Relation;
import com.github.tomap.modeler.model.diagramClass.relation.SimpleRelation;

public class TestRelation {

	// pour ceux qui veulent faire du Junit ... :)

	public void testGeneralization() throws BadTypeException {
		A_Class_Diagram d = new A_Class_Diagram();

		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);

		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);

		A_Class c2 = new A_Class("c2", false, false, false, p1);
		p1.addClass(c1);

		Generalization g = new Generalization(c2, c1);
		d.addRelation(g);
		
		System.out.println(d.display());
	}
	
	public void testImplementation() throws BadTypeException {
		A_Class_Diagram d = new A_Class_Diagram();

		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);

		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);

		An_Interface i1 = new An_Interface("i1", p1);
		p1.addInterface(i1);

		Implementation p = new Implementation(c1, i1);
		d.addRelation(p);
		
		System.out.println(d.display());
	}
	
	public void testComposition() throws BadTypeException {
		A_Class_Diagram d = new A_Class_Diagram();

		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);

		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);

		A_Class c2 = new A_Class("c2", false, false, false, p1);
		p1.addClass(c1);

		
		Composition c = new Composition("works");
		DoubleMultiplicity m1 = new DoubleMultiplicity(0, 2, "att1",c1,c);
		SingleMultiplicity m2 = new SingleMultiplicity(2, "att2", c2, c);
		c.updateMultiplicities(m1, m2);
		
		
		d.addRelation(c);
		
		System.out.println(d.display());
	}
	
	public void testAgregation() throws BadTypeException {
		A_Class_Diagram d = new A_Class_Diagram();

		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);

		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);

		A_Class c2 = new A_Class("c2", false, false, false, p1);
		p1.addClass(c1);

		
		Agregation a = new Agregation("works");
		DoubleMultiplicity m1 = new DoubleMultiplicity(0, 2, "att1",c1,a);
		SingleMultiplicity m2 = new SingleMultiplicity(2, "att2", c2, a);
		a.updateMultiplicities(m1, m2);
		
		d.addRelation(a);
		
		System.out.println(d.display());
	}
	
	public void testSimpleRelation() throws BadTypeException {
		A_Class_Diagram d = new A_Class_Diagram();

		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);

		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);

		A_Class c2 = new A_Class("c2", false, false, false, p1);
		p1.addClass(c1);

		
		SimpleRelation sr = new SimpleRelation("works");
		NoMultiplicity m1 = new NoMultiplicity(c1, sr);
		NoMultiplicity m2 = new NoMultiplicity(c2, sr);
		sr.updateMultiplicities(m1, m2);
		
		d.addRelation(sr);
		
		System.out.println(d.display());
	}
	
	public void testAssociation() throws BadTypeException {
		
		A_Class_Diagram d = new A_Class_Diagram();

		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);

		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);

		A_Class c2 = new A_Class("c2", false, false, false, p1);
		p1.addClass(c1);
		
		A_Class c3 = new A_Class("c3", false, false, true, p1);
		p1.addClass(c3);

		
		Agregation a = new Agregation("works");
		DoubleMultiplicity m1 = new DoubleMultiplicity(0, 2, "att1",c1,a);
		SingleMultiplicity m2 = new SingleMultiplicity(2, "att2", c2, a);
		a.updateMultiplicities(m1, m2);
		
		Association ass = new Association(c3, a);
		d.addRelation(ass);
		
		System.out.println(d.display());
	}
	
	public void testAssociationNary() throws BadTypeException {
		A_Class_Diagram d = new A_Class_Diagram();

		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);

		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);

		A_Class c2 = new A_Class("c2", false, false, false, p1);
		p1.addClass(c1);
		
		A_Class c3 = new A_Class("c3", false, false, true, p1);
		p1.addClass(c3);

		A_Class c4 = new A_Class("c4", false, false, true, p1);
		p1.addClass(c3);
		
		
		SimpleRelation r = new SimpleRelation("");
		SingleMultiplicity m1 = new SingleMultiplicity(2, "att1", c1, r);
		DoubleMultiplicity m2 = new DoubleMultiplicity(0, 2, "att2",c2,r);
		DoubleMultiplicity m3 = new DoubleMultiplicity(0, 7, "att3",c3,r);
		
		N_Relation nr = new N_Relation("my association");
		nr.addMultiplicity(m1);
		nr.addMultiplicity(m2);
		nr.addMultiplicity(m3);
		
		Association ass = new Association(c4, nr);
		
		d.addRelation(ass);
		
		System.out.println(d.display());
	}
}
