package com.github.tomap.modeler.controler;

import java.util.LinkedList;
import java.util.List;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;
import com.github.tomap.modeler.view.DrawClassPane;
import com.github.tomap.modeler.view.DClass.G_Interface;
import com.github.tomap.modeler.view.DClass.G_Relation;

public class ListenerGInterface implements InternalFrameListener {

	/**
	 * <h4>ListenerGInterface allows to listen the G_Interface class</h4>
	 * 
	 * @see G_Interface
	 * @author Alexis CHRETIENNE
	 */

	// ----------------------------------------- //
	// --------------- ATTRIBUTES -------------- //
	// ----------------------------------------- //

	private DrawClassPane drawClassPane;
	private G_Interface frame;

	// ----------------------------------------- //
	// ------------- CONSTRUCTOR---------------- //
	// ----------------------------------------- //

	/**
	 * Constructor
	 * 
	 */

	public ListenerGInterface(G_Interface frame, DrawClassPane drawClassPane) {
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
		
		List<Relation> listRelationToDel = new LinkedList<Relation>();
		List<G_Relation> listGrelationToDel = new LinkedList<G_Relation>();
		
		// get the specific interface to delete
		An_Interface i = frame.getAnInterface();
			
		// delete all relations depending on this interface (model + ui)
		for (int j = 0; j<drawClassPane.getListRelations().size(); j++){
			
			G_Relation gr= drawClassPane.getListRelations().get(j);
			Relation r = drawClassPane.getListRelations().get(j).getaRelation();
			
			if (gr.getListInterfacesUsed().containsKey(i)){
				listRelationToDel.add(r);
				listGrelationToDel.add(gr);
			}
		}
		
		// Cannot delete directly since previous loop, pointer problem. c is deleted in the fist time !
		for(int j = 0; j < listRelationToDel.size(); j++){
			drawClassPane.getDiagram().removeRelation(listRelationToDel.get(j));
		}
		
		for(int j = 0; j < listGrelationToDel.size(); j++){
			drawClassPane.getListRelations().remove(listGrelationToDel.get(j));
		}
		
		// delete the interface (model + ui)
		drawClassPane.getListInterfaces().remove(i);
		i.getaPackage().removeInterface(i);
		drawClassPane.repaint();
		
		System.out.println(drawClassPane.getDiagram().display());
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
