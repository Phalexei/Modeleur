package com.github.tomap.modeler.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.github.tomap.modeler.model.diagramClass.A_Class_Diagram;

@SuppressWarnings("serial")
public class ContainerTabbedPane extends JTabbedPane {
	/**
	 * <h4>ContainerTabbedPane manages the tabbed pane</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //

	public final static Integer SIZE_X = 4 * GlobalContainer.SIZE_X / 5;
	public final static Integer SIZE_Y = GlobalContainer.SIZE_Y;

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //

	private DrawClassPane panelClass;
	private JComponent panelObject;
	private A_Class_Diagram diagram;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //

	/**
	 * Constructor
	 */
	public ContainerTabbedPane() {
		this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		diagram = new A_Class_Diagram();

		panelClass = new DrawClassPane(diagram);
		panelClass.setBackground(new Color(218, 224, 241));
		this.addTab("Class", panelClass);
		this.setMnemonicAt(0, KeyEvent.VK_1);

		panelObject = makeTextPanel("Panel #2");
		panelObject.setBackground(new Color(218, 224, 241));
		this.addTab("Object", panelObject);
		this.setMnemonicAt(1, KeyEvent.VK_2);
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //
	private JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	// ----------------------------------------- //
	// ---------------GETTERS------------------- //
	// ----------------------------------------- //

	public DrawClassPane getPanelClass() {
		return panelClass;
	}

	public JComponent getPanelObject() {
		return panelObject;
	}

	public A_Class_Diagram getDiagram() {
		return diagram;
	}

	// ----------------------------------------- //
	// ----------------SETTERS------------------ //
	// ----------------------------------------- //
	public void setPanelClass(DrawClassPane panelClass) {
		this.panelClass = panelClass;
	}

	public void setPanelObject(JComponent panelObject) {
		this.panelObject = panelObject;
	}

}