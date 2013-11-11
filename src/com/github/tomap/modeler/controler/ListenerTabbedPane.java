package com.github.tomap.modeler.controler;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.tomap.modeler.view.ContainerTabbedPane;
import com.github.tomap.modeler.view.GlobalContainer;

public class ListenerTabbedPane implements ChangeListener{
	/**
	 * <h4>ListenerTabbedPane listens the tabbed pane</h4>
	
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //
		
	private final static int CLASS_SELECTOR		= 0;
	private final static int OBJECT_SELECTOR	= 1;
	
	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //
		
	private GlobalContainer	cGlobal;
	private ContainerTabbedPane containerTabbedPane;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ------------------------------------------//
	
	public ListenerTabbedPane(GlobalContainer cGlobal) {
		super();
		this.cGlobal = cGlobal;
		this.containerTabbedPane = cGlobal.getContainerTabbedPane();
		this.containerTabbedPane.addChangeListener(this);
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (containerTabbedPane.getSelectedIndex() == CLASS_SELECTOR){
			cGlobal.getEditorContainer().loadPanelClass();
		}else if (containerTabbedPane.getSelectedIndex() == OBJECT_SELECTOR){
			cGlobal.getEditorContainer().loadPanelObject();
		}
	}
	
}