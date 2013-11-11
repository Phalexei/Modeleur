package com.github.tomap.modeler.model.diagramClass.test;

import com.github.tomap.modeler.model.diagramClass.A_Class_Diagram;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.model.diagramClass.excpetion.BadTypeException;
import com.github.tomap.modeler.model.diagramClass.type.AString;
import com.github.tomap.modeler.model.diagramClass.type.Double;
import com.github.tomap.modeler.model.diagramClass.type.Float;
import com.github.tomap.modeler.model.diagramClass.type.Integer;
import com.github.tomap.modeler.model.diagramClass.typedElement.Attribute;
import com.github.tomap.modeler.model.diagramClass.typedElement.Method;
import com.github.tomap.modeler.model.diagramClass.typedElement.Parameter;
import com.github.tomap.modeler.model.diagramClass.visibility.Private;
import com.github.tomap.modeler.model.diagramClass.visibility.Protected;
import com.github.tomap.modeler.model.diagramClass.visibility.Public;

public class TestClass {
	
	// pour ceux qui veulent faire du Junit ... :)
	
	public void testSimpleClass() throws BadTypeException{
		
		A_Class_Diagram d = new A_Class_Diagram();
		
		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);
		
		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);
		
		A_Class c2 = new A_Class("c2", false, false, true, p1);
		p1.addClass(c2);
		
		Attribute a1 = new Attribute("att1", true, new Integer(), true, false, c1, new Public());
		Attribute a2 = new Attribute("att2", false, new Double(), false, false, c1, new Private());
		Attribute a3 = new Attribute("att3", false, new Integer(), false, false, c1, new Protected());
		c1.addAttribute(a1);
		c1.addAttribute(a2);
		c1.addAttribute(a3);
		
		Method m1 = new Method(new Private(), new Double(),"method1", false, false, c1);
		Method m2 = new Method(new Private(), new Integer(),"method2", false, true, c1);
		
		c1.addMethod(m1);
		c1.addMethod(m2);
		
		Parameter arg1 = new Parameter("arg0", false, new Integer(), m1);
		Parameter arg2 = new Parameter("arg1", false, new Float(), m1);
		m1.addParameter(arg1);
		m1.addParameter(arg2);
		
		m2.addParameter(arg1);
		
		System.out.println(d.display());
	}
	
	public void testRemoveMethodAtt() throws BadTypeException{
		
		A_Class_Diagram d = new A_Class_Diagram();
		
		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);
		
		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);
		
		Attribute a1 = new Attribute("att1", true, new Integer(), true, false, c1, new Public());
		Attribute a2 = new Attribute("att2", false, new Double(), false, false, c1, new Private());
		Attribute a3 = new Attribute("att3", false, new Integer(), false, false, c1, new Protected());
		c1.addAttribute(a1);
		c1.addAttribute(a2);
		c1.addAttribute(a3);
		
		c1.removeAttribute(a1);
		
		Method m1 = new Method(new Private(), new Double(),"method1", false, false, c1);
		Method m2 = new Method(new Private(), new Integer(),"method2", false, true, c1);
		
		c1.addMethod(m1);
		c1.addMethod(m2);
		
		Parameter arg1 = new Parameter("arg0", false, new Integer(), m1);
		Parameter arg2 = new Parameter("arg1", false, new Float(), m1);
		m1.addParameter(arg1);
		m1.addParameter(arg2);
		m2.addParameter(arg1);
		
		m1.removeParameter(arg1);
		
		System.out.println(d.display());
	}
	
	public void testInterface() throws BadTypeException{
		
		A_Class_Diagram d = new A_Class_Diagram();
		
		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);
		
		An_Interface i = new An_Interface("interface1", p1);
		p1.addInterface(i);
		
		Method m1 = new Method(new Private(), new Double(),"method1", false, false, i);
		Method m2 = new Method(new Private(), new Integer(),"method2", false, true, i);
		
		i.addMethod(m1);
		i.addMethod(m2);
		
		System.out.println(d.display());
	}
	
	public void testMethodParam() throws BadTypeException{
		
		
		A_Class_Diagram d = new A_Class_Diagram();
		
		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);
		
		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);
		
		Attribute a1 = new Attribute("att1", true, new Integer(), true, false, c1, new Public());
		Attribute a2 = new Attribute("att2", false, new Double(), false, false, c1, new Private());
		Attribute a3 = new Attribute("att3", false, new Integer(), false, false, c1, new Protected());
		c1.addAttribute(a1);
		c1.addAttribute(a2);
		c1.addAttribute(a3);
		
		
		
		Method m1 = new Method(new Private(), new Double(),"method1", false, false, c1);
		
		c1.addMethod(m1);
		
		Parameter arg1 = new Parameter("arg1", false, new Integer(), m1);
		Parameter arg2 = new Parameter("arg2", false, new Float(), m1);
		Parameter arg3 = new Parameter("arg3", false, new AString(), m1);
		m1.addParameter(arg1);
		m1.addParameter(arg2);
		m1.addParameter(arg3);
		
		m1.removeParameter(arg1);
		m1.removeParameter(arg2);
		c1.removeAttribute(a1);
		c1.removeMethod(m1);
		
		System.out.println(d.display());
	}

	public void testremoveClass() throws BadTypeException{
	
		A_Class_Diagram d = new A_Class_Diagram();
		
		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);
		
		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);
		
		Attribute a1 = new Attribute("att1", true, new Integer(), true, false, c1, new Public());
		Attribute a2 = new Attribute("att2", false, new Double(), false, false, c1, new Private());
		Attribute a3 = new Attribute("att3", false, new Integer(), false, false, c1, new Protected());
		c1.addAttribute(a1);
		c1.addAttribute(a2);
		c1.addAttribute(a3);
		
		Method m1 = new Method(new Private(), new Double(),"method1", false, false, c1);
		Method m2 = new Method(new Private(), new Integer(),"method2", false, true, c1);
		
		c1.addMethod(m1);
		c1.addMethod(m2);
		
		Parameter arg1 = new Parameter("arg0", false, new Integer(), m1);
		Parameter arg2 = new Parameter("arg1", false, new Float(), m1);
		m1.addParameter(arg1);
		m1.addParameter(arg2);
		
		m2.addParameter(arg1);
		
		System.out.println(d.display());
		
		p1.removeClass(c1);
		
		System.out.println(d.display());
	
	}
	
	public void testremovePackage() throws BadTypeException{
		
		A_Class_Diagram d = new A_Class_Diagram();
		
		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);
		
		A_Class c1 = new A_Class("c1", false, false, true, p1);
		p1.addClass(c1);
		
		
		System.out.println(d.display());
		
		d.removePackage(p1);
		
		System.out.println(d.display());
	
	}
	
public void testRemoveClassDiagramm() throws BadTypeException{
		
		A_Class_Diagram d = new A_Class_Diagram();
		
		A_Package p1 = new A_Package("p1");
		d.addPackage(p1);
		
		System.out.println(d.display());
		
		d.removePackage(p1);
		
		d = null;
		
		System.out.println(d.display());
	
	}


}
