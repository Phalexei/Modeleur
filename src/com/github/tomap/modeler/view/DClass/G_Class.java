package com.github.tomap.modeler.view.DClass;

import com.github.tomap.modeler.main.Main;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.github.tomap.modeler.view.DrawClassPane;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;

public class G_Class extends JInternalFrame {

	/**
	 * <h4>G_Class Allows to represent a class (UI)</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //
	private static final long serialVersionUID = 1L;

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //
	private A_Class aClass;
	private DrawClassPane drawclassPane;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */

	public G_Class(A_Class aclass, DrawClassPane drawclassPane) {
		super(aclass.getaPackage().getName());
		this.aClass = aclass;
		this.drawclassPane = drawclassPane;

		this.setSize(200, 200);
		this.setLocation(15, 15);
		this.setVisible(true);

		/*
		 * UIManager.put("InternalFrame.activeTitleBackground", new
		 * ColorUIResource(new Color(248,250,175)));
		 * UIManager.put("InternalFrame.inactiveTitleBackground", new
		 * ColorUIResource(new Color(248,250,175)));
		 * javax.swing.plaf.basic.BasicInternalFrameUI ui = new
		 * javax.swing.plaf.basic.BasicInternalFrameUI(this); this.setUI(ui);
		 */

		String path = "";
		if (aClass.isAbstract()) {
			path = Main._ICON_PATH + "abstract.png";
		} else {
			path = Main._ICON_PATH + "class.png";
		}

		java.net.URL imgURL = getClass().getResource(path);
		ImageIcon img = new ImageIcon(imgURL);
		this.setFrameIcon(img);

		// Initialise Panel 1 & Components
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));

		String s = aClass.isStatic() ? "Static " : "";
		s += aClass.isFinal() ? "Final " : "";
		s += aClass.isAbstract() ? "Abstract" : "";

		if (!s.isEmpty()) {
			JLabel label1 = new JLabel(s);
			p1.add(label1);
		}

		JLabel label2 = new JLabel(aClass.getName());
		p1.add(label2);

		p1.add(new JSeparator());

		JPanel attributePanel = new PanelAttibuteClass(this.aClass);
		p1.add(attributePanel);

		p1.add(new JSeparator());

		JPanel methodPanel = new PanelMethodClass(this.aClass);
		p1.add(methodPanel);

		this.add(p1);
		this.pack();

		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentMoved(ComponentEvent e) {
				G_Class.this.drawclassPane.repaint();
			}
		});
		this.closable = true;
		this.resizable = true;

	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// ---------------GETTERS/SETTERS----------- //
	// ----------------------------------------- //
	
	public A_Class getaClass() {
		return aClass;
	}

	
}
