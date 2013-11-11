package com.github.tomap.modeler.view.DClass;

import com.github.tomap.modeler.main.Main;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.github.tomap.modeler.view.DrawClassPane;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;

public class G_Interface extends JInternalFrame {

	/**
	 * <h4>G_Interface Allows to represent an interface (UI)</h4>
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

	private An_Interface anInterface;
	private DrawClassPane drawclassPane;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */

	public G_Interface(An_Interface i, DrawClassPane drawclassPane) {
		super(i.getaPackage().getName());
		this.anInterface = i;
		this.drawclassPane = drawclassPane;
		
		this.setLocation(15, 15);
		this.setVisible(true);

		java.net.URL imgURL = getClass().getResource(
				Main._ICON_PATH + "interface.png");
		ImageIcon img = new ImageIcon(imgURL);
		this.setFrameIcon(img);

		JPanel p1 = new JPanel();
		JLabel label2 = new JLabel(anInterface.getName());
		p1.add(label2);

		JPanel p2 = new PanelMethodInterface(anInterface);

		JPanel p3 = new JPanel(new BorderLayout());
		p3.add(p1, BorderLayout.NORTH);
		p3.add(new JSeparator());
		p3.add(p2);
		this.add(p3);

		this.pack();

		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentMoved(ComponentEvent e) {
				G_Interface.this.drawclassPane.repaint();
			}
		});
		this.closable = true;
		this.resizable = true;

	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	public An_Interface getAnInterface() {
		return anInterface;
	}

	public void setAnInterface(An_Interface anInterface) {
		this.anInterface = anInterface;
	}

}
