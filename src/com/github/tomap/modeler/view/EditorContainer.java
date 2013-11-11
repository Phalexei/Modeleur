package com.github.tomap.modeler.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class EditorContainer extends JSplitPane {
	/**
	 * <h4>EditorContainer Allows to edit a UML diagram</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //

	public static Integer SIZE_X = 200;
	public static Integer SIZE_Y = GlobalContainer.SIZE_Y;

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //

	private SelectorClass panelClass;
	private SelectorObject panelObject;
	private PanelEditor panelEditor;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */
	public EditorContainer() {
		super();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		this.setOrientation(JSplitPane.VERTICAL_SPLIT);

		panelObject = new SelectorObject();
		panelClass = new SelectorClass();
		panelEditor = new PanelEditor();

		loadPanelClass();
		this.setBottomComponent(panelEditor);
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	public void loadPanelObject() {
		//JScrollPane viewClass = new JScrollPane(panelObject);
		this.setTopComponent(panelObject);
		this.setDividerLocation(300);
	}

	public void loadPanelClass() {
		
		//JScrollPane viewClass = new JScrollPane(panelClass);
		this.setTopComponent(panelClass);
		this.setDividerLocation(300);
	}

	// ----------------------------------------- //
	// ---------------GETTERS------------------- //
	// ----------------------------------------- //
	public SelectorClass getPanelClass() {
		return panelClass;
	}

	public SelectorObject getPanelObject() {
		return panelObject;
	}

	public PanelEditor getPanelEditor() {
		return panelEditor;
	}

	// ----------------------------------------- //
	// ----------------SETTERS------------------ //
	// ----------------------------------------- //
	public void setPanelClass(SelectorClass panelClass) {
		this.panelClass = panelClass;
	}

	public void setPanelObject(SelectorObject panelObject) {
		this.panelObject = panelObject;
	}

	public void setPanelEditor(PanelEditor panelEditor) {
		this.panelEditor = panelEditor;
	}
}
