package com.github.tomap.modeler.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDesktopPane;

import com.github.tomap.modeler.controler.ListenerGInterface;
import com.github.tomap.modeler.controler.ListenerGclass;
import com.github.tomap.modeler.model.diagramClass.A_Class_Diagram;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;
import com.github.tomap.modeler.view.DClass.G_Class;
import com.github.tomap.modeler.view.DClass.G_Interface;
import com.github.tomap.modeler.view.DClass.G_Relation;

public class DrawClassPane extends JDesktopPane {

	/**
	 * <h4>DrawClassPane Allows to displays class diagram</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	
	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //

	private static final long serialVersionUID = 1L;
	private A_Class_Diagram diagram;

	//private List<G_Class> listClass;
	private List<G_Relation> listRelations;
	//private List<G_Interface> listInterface;
	
	private HashMap<A_Class, G_Class> listClasses;
	private HashMap<An_Interface, G_Interface> listInterfaces;
	//private HashMap<Relation, G_Relation> listRelation;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */
	public DrawClassPane(A_Class_Diagram diagram) {
		this.diagram = diagram;
		
		listRelations = new LinkedList<G_Relation>();
		listClasses = new HashMap<A_Class, G_Class>();
		listInterfaces = new HashMap<An_Interface, G_Interface>();
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	

	/**
	 * 
	 * @param a_package
	 * @param a_class
	 * @param isAbstract
	 * @param isFinal
	 * @param isStatic
	 */
	public void addClass(String a_package, String a_class, boolean isAbstract,
			boolean isFinal, boolean isStatic) {

		// update model
		A_Class c = new A_Class(a_class, isFinal, isStatic, isAbstract, null);

		if (diagram.getListPackages().containsKey(a_package)) {
			A_Package p = diagram.getListPackages().get(a_package);
			c.setaPackage(p);
			p.addClass(c);
		} else {
			A_Package p = new A_Package(a_package);
			c.setaPackage(p);
			p.addClass(c);
			diagram.addPackage(p);
		}

		// update ui
		G_Class frame1 = new G_Class(c, this);
		@SuppressWarnings("unused")
		ListenerGclass listener = new ListenerGclass(frame1, this);
		
		listClasses.put(c, frame1);
		this.add(frame1);
		
		System.out.println(diagram.display());
	}

	/**
	 * 
	 * @param a_package
	 * @param an_interface
	 */
	public void addInterface(String a_package, String an_interface) {

		// update model
		An_Interface i = new An_Interface(an_interface, null);

		if (diagram.getListPackages().containsKey(a_package)) {
			A_Package p = diagram.getListPackages().get(a_package);
			i.setaPackage(p);
			p.addInterface(i);
		} else {
			A_Package p = new A_Package(a_package);
			i.setaPackage(p);
			p.addInterface(i);
			diagram.addPackage(p);
		}

		// update ui
		G_Interface frame1 = new G_Interface(i, this);
		@SuppressWarnings("unused")
		ListenerGInterface listener = new ListenerGInterface(frame1, this);
		listInterfaces.put(i, frame1);
		this.add(frame1);
		
		System.out.println(diagram.display());
	}

	
	public void addRelation(Relation r){
		//update model
		this.diagram.addRelation(r);
		
		//update ui
		G_Relation grelation = new G_Relation(r, listClasses, listInterfaces);
		this.listRelations.add(grelation);
		System.out.println(diagram.display());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(G_Relation r : listRelations){
			r.paintRelation(g2d);
		}
	}
	
	
	// ----------------------------------------- //
	// ---------------GETTERS------------------- //
	// ----------------------------------------- //
	public A_Class_Diagram getDiagram() {
		return diagram;
	}

	public HashMap<A_Class, G_Class> getListClasses() {
		return listClasses;
	}

	public void setListClasses(HashMap<A_Class, G_Class> listClasses) {
		this.listClasses = listClasses;
	}

	public HashMap<An_Interface, G_Interface> getListInterfaces() {
		return listInterfaces;
	}

	public void setListInterfaces(HashMap<An_Interface, G_Interface> listInterfaces) {
		this.listInterfaces = listInterfaces;
	}
	public List<G_Relation> getListRelations() {
		return listRelations;
	}

	public void setListRelations(List<G_Relation> listRelations) {
		this.listRelations = listRelations;
	}
}
