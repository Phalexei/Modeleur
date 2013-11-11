package com.github.tomap.modeler.view.DClass;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.HashMap;
import javax.swing.JInternalFrame;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.relation.Agregation;
import com.github.tomap.modeler.model.diagramClass.relation.Association;
import com.github.tomap.modeler.model.diagramClass.relation.BinaryRelation;
import com.github.tomap.modeler.model.diagramClass.relation.Composition;
import com.github.tomap.modeler.model.diagramClass.relation.Generalization;
import com.github.tomap.modeler.model.diagramClass.relation.Implementation;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;
import com.github.tomap.modeler.model.diagramClass.relation.SimpleRelation;

public class G_Relation {

	/**
	 * <h4>G_Class Allows to represent a relation (UI)</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private static final Stroke s = new BasicStroke(1.0f);
	private static final int barb = 20; // barb length
	private final static double phi = Math.PI / 6; // 30 degrees barb angle

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //
	private Relation aRelation;
	private HashMap<A_Class, G_Class> listClasses;
	private HashMap<An_Interface, G_Interface> listInterfaces;
	
	private HashMap<A_Class, G_Class> listClassesUsed;
	private HashMap<An_Interface, G_Interface> listInterfacesUsed;
	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 * @param listInterfaces 
	 * @param listClasses 
	 */

	public G_Relation(Relation aRelation, HashMap<A_Class, G_Class> listClasses, HashMap<An_Interface, G_Interface> listInterfaces) {
		
		this.aRelation = aRelation;
		this.listClasses = listClasses;
		this.listInterfaces = listInterfaces;
		
		System.out.println(listClasses.size());
		
		this.listClassesUsed = new HashMap<A_Class, G_Class>();
		this.listInterfacesUsed = new HashMap<An_Interface, G_Interface>();

	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	public void paintRelation(Graphics2D g){
		
		if(aRelation instanceof Association){
			Association ass = (Association) aRelation;
			makeBinaryAssociation(ass, g);
		}
		else if (aRelation instanceof SimpleRelation){
			SimpleRelation s = (SimpleRelation) aRelation;
			makeBinaryRelation(s, g, false, false);
		} else if (aRelation instanceof Composition){
			Composition c = (Composition) aRelation;
			makeBinaryRelation(c, g, true, false);
		} else if (aRelation instanceof Agregation){
			Agregation a = (Agregation) aRelation;
			makeBinaryRelation(a, g, false, true);
		} else if (aRelation instanceof Generalization){
			Generalization gz = (Generalization)aRelation;
			String relationame = gz.getName();
			if(gz.getFrom() instanceof A_Class){
				A_Class cfrom = (A_Class) gz.getFrom();
				A_Class cTo = (A_Class) gz.getTo();
				G_Class from = listClasses.get(cfrom);
				G_Class to = listClasses.get(cTo);
				listClassesUsed.put(cfrom, from);
				listClassesUsed.put(cTo, to);
				drawGeneralization(g, from, to, relationame);
			}else{
				An_Interface cfrom = (An_Interface) gz.getFrom();
				An_Interface cTo = (An_Interface) gz.getTo();
				G_Interface from = listInterfaces.get(cfrom);
				G_Interface to = listInterfaces.get(cTo);
				listInterfacesUsed.put(cfrom, from);
				listInterfacesUsed.put(cTo, to);
				drawGeneralization(g, from, to, relationame);
			}
		} else if (aRelation instanceof Implementation){
			Implementation i = (Implementation)aRelation;
			A_Class cfrom = i.getaClass();
			An_Interface iTo = i.getAnInterface();
			G_Class from = listClasses.get(cfrom);
			G_Interface to = listInterfaces.get(iTo);
			listClassesUsed.put(cfrom, from);
			listInterfacesUsed.put(iTo, to);
			String relationame = i.getName();
			
			drawImplementation(g, from, to, relationame);
		}
	}
	
	private Point makeBinaryRelation(BinaryRelation r, Graphics2D g,boolean isComposition, boolean isAgregation){
		
		A_Class cfrom = r.getMultiplicityFrom().getaClass();
		A_Class cTo = r.getMultiplicityTo().getaClass();
		G_Class from = listClasses.get(cfrom);
		G_Class to = listClasses.get(cTo);
		listClassesUsed.put(cfrom, from);
		listClassesUsed.put(cTo, to);
		
		String relationame = r.getName();
		String multfrom = r.getMultiplicityFrom().getMultiplicities();
		String multto = r.getMultiplicityTo().getMultiplicities();
		
		drawBinaryRelation(g, from, to, relationame, multfrom, multto,isComposition, isAgregation);
		
		return calculateMiddlePoint(from, to);
	}
	
	private void makeBinaryAssociation(Association ass, Graphics2D g){
		A_Class cAssociative = ass.getAssociativeClass();
		G_Class fassociative = listClasses.get(cAssociative);
		listClassesUsed.put(cAssociative, fassociative);
		Relation r = ass.getRelation();
		Point p = new Point(0, 0);
		
		if (r instanceof SimpleRelation){
			SimpleRelation s = (SimpleRelation) r;
			p = makeBinaryRelation(s, g, false, false);
		}else if (r instanceof Composition){
			Composition c = (Composition) r;
			p = makeBinaryRelation(c, g, true, false);
		}else if (r instanceof Agregation){
			Agregation a = (Agregation) r;
			p = makeBinaryRelation(a, g, false, true);
		}
		
		drawBinaryAssociation(g, p, fassociative);
		
	}
	
	private Point calculateMiddlePoint( JInternalFrame f1,JInternalFrame f2){
		int xmilieu = 0;
		int ymilieu = 0;
		
		int x1 = f1.getX() + f1.getWidth();
		int y1 = f1.getY() + f1.getHeight() / 2;
		int x2 = f2.getX();// + two.getWidth() ;
		int y2 = f2.getY() + f2.getHeight() / 2;
		
		xmilieu = (x1+x2) / 2;
		ymilieu = (y1+y2) / 2;
		

		return new Point(xmilieu, ymilieu);
	}
	
	private void drawBinaryAssociation(Graphics2D g2, Point middle, JInternalFrame fassociative) {

		float[] dash3 = { 4f, 0f, 2f };
		BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f);

		int x1 = fassociative.getX() + fassociative.getWidth() / 2;
		int y1 = fassociative.getY() + fassociative.getHeight() / 2;
		g2.setStroke(bs3);
		g2.draw(new Line2D.Double(x1, y1, middle.getX(), middle.getY()));
		
		g2.setStroke(s);
		g2.setColor(Color.black);
	}
	
	private void drawBinaryRelation(Graphics2D g2, JInternalFrame f1,JInternalFrame f2, String name, String m1, String m2, 
			boolean isComposition, boolean isAgregation) {
		g2.setColor(Color.black);
		g2.setStroke(s);

		int x1 = f1.getX() + f1.getWidth();
		int y1 = f1.getY() + f1.getHeight() / 2;
		int x2 = f2.getX();// + two.getWidth() ;
		int y2 = f2.getY() + f2.getHeight() / 2;

		g2.draw(new Line2D.Double(x1, y1, x2, y2));

		double theta = Math.atan2(y2 - y1, x2 - x1);
		if (isComposition) {
			drawArrowComposition(g2, theta, x2, y2);
		}else if (isAgregation) {
			drawArrowAgregation(g2, theta, x2, y2);
		}
		
		g2.drawString(name, (x1 + x2) / 2 - 20, (y1 + y2) / 2);
		g2.drawString(m1, x1 + 10, y1);
		g2.drawString(m2, x2 - 30, y2);
	}
	
	private void drawArrowComposition(Graphics2D g2, double theta, double x0,double y0) {
		int x = (int) (x0 - barb * Math.cos(theta + phi));
		int y = (int) (y0 - barb * Math.sin(theta + phi));
		g2.draw(new Line2D.Double(x0, y0, x, y));

		int x1 = (int) (x0 - barb * Math.cos(theta - phi));
		int y1 = (int) (y0 - barb * Math.sin(theta - phi));
		g2.draw(new Line2D.Double(x0, y0, x, y));

		int x2 = (int) (x0 - 2 * barb * Math.cos(theta + phi));
		int y2 = (int) (y0 - 2 * barb * Math.sin(theta + phi));
		int x3 = (int) (x0 - 2 * barb * Math.cos(theta - phi));
		int y3 = (int) (y0 - 2 * barb * Math.sin(theta - phi));

		int x5 = (x2 + x3) / 2;
		int y5 = (y2 + y3) / 2;

		int[] xPolygon = { (int) x0, x1, x5, x };
		int[] yPolygon = { (int) y0, y1, y5, y };

		g2.setColor(Color.black);
		Polygon p = new Polygon(xPolygon, yPolygon, 4);
		g2.drawPolygon(p);
		g2.fill(p);

	}

	private void drawArrowAgregation(Graphics2D g2, double theta, double x0,double y0) {
		int x = (int) (x0 - barb * Math.cos(theta + phi));
		int y = (int) (y0 - barb * Math.sin(theta + phi));
		g2.draw(new Line2D.Double(x0, y0, x, y));

		int x1 = (int) (x0 - barb * Math.cos(theta - phi));
		int y1 = (int) (y0 - barb * Math.sin(theta - phi));
		g2.draw(new Line2D.Double(x0, y0, x, y));

		int x2 = (int) (x0 - 2 * barb * Math.cos(theta + phi));
		int y2 = (int) (y0 - 2 * barb * Math.sin(theta + phi));
		int x3 = (int) (x0 - 2 * barb * Math.cos(theta - phi));
		int y3 = (int) (y0 - 2 * barb * Math.sin(theta - phi));

		int x5 = (x2 + x3) / 2;
		int y5 = (y2 + y3) / 2;

		int[] xPolygon = { (int) x0, x1, x5, x };
		int[] yPolygon = { (int) y0, y1, y5, y };

		g2.setColor(Color.black);
		Polygon p = new Polygon(xPolygon, yPolygon, 4);
		g2.drawPolygon(p);
	}

	private void drawGeneralization(Graphics2D g2, JInternalFrame f1,JInternalFrame f2, String name) {
		g2.setColor(Color.black);
		g2.setStroke(s);

		int x1 = f1.getX() + f1.getWidth();
		int y1 = f1.getY() + f1.getHeight() / 2;
		int x2 = f2.getX();// + two.getWidth() ;
		int y2 = f2.getY() + f2.getHeight() / 2;

		g2.draw(new Line2D.Double(x1, y1, x2, y2));

		double theta = Math.atan2(y2 - y1, x2 - x1);
		drawArrow(g2, theta, x2, y2);

		g2.drawString(name, (x1 + x2) / 2 - 20, (y1 + y2) / 2);
	}

	private void drawImplementation(Graphics2D g2, JInternalFrame f1,JInternalFrame f2, String name) {
		float[] dash3 = { 4f, 0f, 2f };
		BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f);

		g2.setColor(Color.black);

		g2.setStroke(bs3);

		int x1 = f1.getX() + f1.getWidth();
		int y1 = f1.getY() + f1.getHeight() / 2;
		int x2 = f2.getX();// + two.getWidth() ;
		int y2 = f2.getY() + f2.getHeight() / 2;

		g2.draw(new Line2D.Double(x1, y1, x2, y2));
		g2.setStroke(s);

		double theta = Math.atan2(y2 - y1, x2 - x1);
		drawArrow(g2, theta, x2, y2);

		g2.drawString(name, (x1 + x2) / 2 - 20, (y1 + y2) / 2);
	}
	
	private void drawArrow(Graphics2D g2, double theta,
			double x0, double y0) {
		int x = (int) (x0 - barb * Math.cos(theta + phi));
		int y = (int) (y0 - barb * Math.sin(theta + phi));
		
		int x1 = (int) (x0 - barb * Math.cos(theta - phi));
		int y1 = (int) (y0 - barb * Math.sin(theta - phi));

		int[] xPolygon = { (int) x0, x, x1 };
		int[] yPolygon = { (int) y0, y, y1 };

		Polygon p = new Polygon(xPolygon, yPolygon, 3);

		g2.drawPolygon(p);
		
	}
	
	
	// ----------------------------------------- //
	// ---------------GETTERS/SETTERS----------- //
	// ----------------------------------------- //
	public Relation getaRelation() {
		return aRelation;
	}

	public void setaRelation(Relation aRelation) {
		this.aRelation = aRelation;
	}

	public HashMap<A_Class, G_Class> getListClassesUsed() {
		return listClassesUsed;
	}

	public void setListClassesUsed(HashMap<A_Class, G_Class> listClassesUsed) {
		this.listClassesUsed = listClassesUsed;
	}

	public HashMap<An_Interface, G_Interface> getListInterfacesUsed() {
		return listInterfacesUsed;
	}

	public void setListInterfacesUsed(
			HashMap<An_Interface, G_Interface> listInterfacesUsed) {
		this.listInterfacesUsed = listInterfacesUsed;
	}
	
	
	
}
