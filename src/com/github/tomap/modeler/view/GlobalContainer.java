package com.github.tomap.modeler.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.github.tomap.modeler.controler.ListenerEditor;
import com.github.tomap.modeler.controler.ListenerTabbedPane;
import com.github.tomap.modeler.view.menu.BarMenu;

@SuppressWarnings("serial")
public class GlobalContainer extends JSplitPane
{
	/**
	 * <h4>GlobalContainer is the main container of the main frame</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //

	public final static Integer	SIZE_X		= MainFrame.SIZE_X;    
	public final static Integer	SIZE_Y	= MainFrame.SIZE_Y - BarMenu.SIZE_Y;

	// ----------------------------------------- //
	// ----------------ATRIBUTS----------------- //
	// ----------------------------------------- //
	/**
	 * contains tabbed pane
	 * 
	 * @see ContainerTabbedPane
	 */
	private ContainerTabbedPane	containerTabbedPane;
	/**
	 * allows to edit uml diagram
	 * 
	 * @see EditorContainer
	 */
	private EditorContainer	editorContainer;
	private JFrame mainframe;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //

	public GlobalContainer(JFrame mainframe)
	{
		this.mainframe = mainframe;
		intiPositionGlobalContainer();
		buildtabbedPane();
		buildContainerEditor();
		
		
	}

	// ----------------------------------------- //
	// -------------METHODS -------------------- //
	// ----------------------------------------- //
	/**
	 * Initializes the global container's position
	 */
	public void intiPositionGlobalContainer()
	{
		this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.setDividerLocation(EditorContainer.SIZE_X);
	}

	/**
	 * Adds the tabbed pane to inside the globale container
	 */
	@SuppressWarnings("unused")
	private void buildtabbedPane()
	{
		containerTabbedPane = new ContainerTabbedPane();
		ListenerTabbedPane l = new ListenerTabbedPane(this);
		this.setRightComponent(containerTabbedPane);
		
	}
	
	/**
	 * Adds the editor container to inside the globale container
	 */
	@SuppressWarnings("unused")
	public void buildContainerEditor()
	{
		editorContainer = new EditorContainer();
		ListenerEditor e = new ListenerEditor(this);
		this.setLeftComponent(editorContainer);
	}

	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //

	public ContainerTabbedPane getContainerTabbedPane() {
		return containerTabbedPane;
	}
	
	public EditorContainer getEditorContainer() {
		return editorContainer;
	}
	
	public JFrame getMainframe() {
		return mainframe;
	}

	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //

	
	public void setMainframe(JFrame mainframe) {
		this.mainframe = mainframe;
	}

	public void setContainerTabbedPane(ContainerTabbedPane containerTabbedPane) {
		this.containerTabbedPane = containerTabbedPane;
	}

	public void setEditorContainer(EditorContainer editorContainer) {
		this.editorContainer = editorContainer;
	}

}
