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

import com.github.tomap.modeler.view.dialog.DialogAttributeClass;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.typedElement.Attribute;

public class PanelAttibuteClass extends JPanel {

	/**
	 * <h4>PanelAttibuteClass Allows to represent the list of classe's
	 * attributes (UI)</h4>
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
	private DefaultListModel<Attribute> model;
	private A_Class a_class;
	private DialogAttributeClass dialogAttribute;
	private JButton removeButton;
	private JButton addButton;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelAttibuteClass(A_Class a_class) {

		this.a_class = a_class;
		setLayout(new BorderLayout());
		model = new DefaultListModel<Attribute>();
		list = new JList(model);

		JScrollPane pane = new JScrollPane(list);
		addButton = new JButton("Add Attribute");
		removeButton = new JButton("Remove Attribute");
		removeButton.setEnabled(false);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogAttribute.setVisible(true);
			}
		});

		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getSize() > 0) {
					int i = list.getSelectedIndex();

					Attribute a = model.get(i);
					PanelAttibuteClass.this.a_class.removeAttribute(a);
					model.removeElementAt(i);
					System.out.println(PanelAttibuteClass.this.a_class
							.display());

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

		dialogAttribute = new DialogAttributeClass(this);
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// ---------------GETTERS/SETTERS----------- //
	// ----------------------------------------- //
	
	public JButton getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(JButton removeButton) {
		this.removeButton = removeButton;
	}

	public DefaultListModel<Attribute> getModel() {
		return model;
	}

	public void setModel(DefaultListModel<Attribute> model) {
		this.model = model;
	}

	@SuppressWarnings("rawtypes")
	public JList getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(JList list) {
		this.list = list;
	}

	public A_Class getA_class() {
		return a_class;
	}

	public void setA_class(A_Class a_class) {
		this.a_class = a_class;
	}

}