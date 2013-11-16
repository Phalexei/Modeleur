package com.github.tomap.modeler.controler;

import java.util.LinkedList;
import java.util.List;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;
import com.github.tomap.modeler.view.DrawClassPane;
import com.github.tomap.modeler.view.DClass.G_Class;
import com.github.tomap.modeler.view.DClass.G_Relation;

public class ListenerGclass extends GFrameController implements InternalFrameListener {

	/**
	 * <h4>ListenerGclass allows to listen the G_Class class</h4>
	 * 
	 * @see G_Class
	 * @author Alexis CHRETIENNE
	 */

	// ----------------------------------------- //
	// --------------- ATTRIBUTES -------------- //
	// ----------------------------------------- //

	private DrawClassPane drawClassPane;
	private G_Class frame;

	// ----------------------------------------- //
	// ------------- CONSTRUCTOR---------------- //
	// ----------------------------------------- //

	/**
	 * Constructor
	 * 
	 */

	public ListenerGclass(G_Class frame, DrawClassPane drawClassPane) {
		super(drawClassPane.getDiagram());
		this.drawClassPane = drawClassPane;
		this.frame = frame;
		frame.addInternalFrameListener(this);
	}

	// ----------------------------------------- //
	// ---------------- METHODS ---------------- //
	// ----------------------------------------- //

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		deleteClassAndAssociation();
	}

	public void deleteClassAndAssociation(){
		List<Relation> listRelationToDel = new LinkedList<Relation>();
		List<G_Relation> listGrelationToDel = new LinkedList<G_Relation>();
		
		System.out.println("========test=========");
		
		// get the specific class to delete
		A_Class c = frame.getaClass();
		
		// delete all relations depending on this class (model + ui)
		for (int i = 0; i<drawClassPane.getListRelations().size(); i++){
			
			G_Relation gr= drawClassPane.getListRelations().get(i);
			Relation r = drawClassPane.getListRelations().get(i).getaRelation();
			System.out.println(r.display());
			if (gr.getListClassesUsed().containsKey(c)){
				listRelationToDel.add(r);
				listGrelationToDel.add(gr);
			}
		}
		
		// Cannot delete directly since previous loop, pointer problem. c is deleted in the fist time !
		for(int i = 0; i < listRelationToDel.size(); i++){
			System.out.println("okk model");
			aclassDiagram.removeRelation(listRelationToDel.get(i));
		}
		
		for(int i = 0; i < listGrelationToDel.size(); i++){
			System.out.println("ok ui");
			drawClassPane.getListRelations().remove(listGrelationToDel.get(i));
		}
		
		// delete the class (model + ui)
		
		drawClassPane.getListClasses().remove(c);
		c.getaPackage().removeClass(c);
		
		drawClassPane.repaint();
		
		
		System.out.println(aclassDiagram.display());
	}
	@Override
	public void internalFrameClosed(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

}
