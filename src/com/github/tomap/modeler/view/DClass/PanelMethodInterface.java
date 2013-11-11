package com.github.tomap.modeler.view.DClass;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.github.tomap.modeler.view.dialog.DialogMethodInterface;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.typedElement.Method;

public class PanelMethodInterface extends JPanel {

	/**
	 * <h4>PanelMethodInterface Allows to represent the list of interface's
	 * methods (UI)</h4>
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

	@SuppressWarnings("rawtypes")
	private JList list;
	private DefaultListModel<Method> model;
	private An_Interface anInterface;
	private DialogMethodInterface dialogMethod;
	private JButton removeButton;
	private JButton addButton;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelMethodInterface(An_Interface anInterface) {

		this.anInterface = anInterface;
		setLayout(new BorderLayout());
		model = new DefaultListModel<Method>();
		list = new JList(model);

		JScrollPane pane = new JScrollPane(list);
		addButton = new JButton("Add Method");
		removeButton = new JButton("Remove Method");
		removeButton.setEnabled(false);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogMethod.setVisible(true);
			}
		});

		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getSize() > 0) {
					int i = list.getSelectedIndex();

					Method m = model.get(i);
					PanelMethodInterface.this.anInterface.removeMethod(m);
					model.removeElementAt(i);
					System.out.println(PanelMethodInterface.this.anInterface.display());

					if (model.getSize() == 0) {
						removeButton.setEnabled(false);
					}
				}

			}
		});

		list.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
					int i = list.getSelectedIndex();
					model.removeElementAt(i);

				}
			}
		});

		add(pane, BorderLayout.NORTH);
		add(addButton, BorderLayout.WEST);
		add(removeButton, BorderLayout.EAST);

		dialogMethod = new DialogMethodInterface(this);
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// ---------------GETTERS/SETTERS----------- //
	// ----------------------------------------- //

	public DefaultListModel<Method> getModel() {
		return model;
	}

	public void setModel(DefaultListModel<Method> model) {
		this.model = model;
	}

	public An_Interface getAnInterface() {
		return anInterface;
	}

	public void setAnInterface(An_Interface anInterface) {
		this.anInterface = anInterface;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(JButton removeButton) {
		this.removeButton = removeButton;
	}

}