package com.github.tomap.modeler.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import com.github.tomap.modeler.view.GlobalContainer;
import com.github.tomap.modeler.controler.ListenerDialogNRelation;
import com.github.tomap.modeler.model.diagramClass.A_Class_Diagram;
import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;

public class DialogNrelation extends JDialog {

	/**
	 * <h4>DialogNrelation listens the N relation dialog</h4>
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

	private JButton valid;
	private JButton cancel;
	private JList<Object> listClasses;
	private JPanel panelAssociativeClass;
	private JPanel panelList;
	private A_Class_Diagram a_classDiagram;
	private GlobalContainer cGlobal;
	private JCheckBox isAssociative;
	private JComboBox<Object> comboAssociativeWith;
	private List<com.github.tomap.modeler.model.diagramClass.type.Type> linkedlistClasses;
	private JPanel panelError;
	private JTextArea areaError;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */

	public DialogNrelation(GlobalContainer cGlobal) {
		super(cGlobal.getMainframe());
		this.cGlobal = cGlobal;
		this.a_classDiagram = cGlobal.getContainerTabbedPane().getDiagram();
		this.setContentPane(createPanels());
		this.pack();
		this.setTitle("Make a relation");
		this.setLocationRelativeTo(cGlobal.getMainframe());
		this.pack();
		this.setLocationRelativeTo(null);

		@SuppressWarnings("unused")
		ListenerDialogNRelation listener = new ListenerDialogNRelation(this);
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	private JPanel createPanels() {

		linkedlistClasses = new LinkedList<>();
		fillListused(false);

		JPanel panel = new JPanel(new BorderLayout());

		panel.add(makePanelTable(), BorderLayout.NORTH);
		panel.add(makePanelAssociativeClass(),BorderLayout.CENTER);
		panel.add(makePanelValidation(),  BorderLayout.SOUTH);

		return panel;
	}

	private Component makePanelTable() {

		panelList = new JPanel(new BorderLayout());
		JLabel l = new JLabel("Please select classes");
		listClasses = new JList<Object>(linkedlistClasses.toArray());
		listClasses.setVisibleRowCount(10);
		listClasses
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		panelList.add(l, BorderLayout.NORTH);
		panelList.add(new JScrollPane(listClasses), BorderLayout.CENTER);
		
		return panelList;
	}

	private JPanel makePanelAssociativeClass() {
		panelAssociativeClass = new JPanel();
		isAssociative = new JCheckBox("Associative", false);

		comboAssociativeWith = new JComboBox<>(linkedlistClasses.toArray());
		comboAssociativeWith.setEnabled(false);

		isAssociative.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				comboAssociativeWith.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});

		panelAssociativeClass.add(isAssociative);
		panelAssociativeClass.add(comboAssociativeWith);

		return panelAssociativeClass;

	}
	
	private Component makePanelError() {
		panelError = new JPanel();
		areaError = new JTextArea(5, 50);
		areaError.setEditable(false);
		areaError.setForeground(Color.red);
		panelError.add(areaError);
		
		return panelError;
	}


	private Component makePanelValidation() {
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(makePanelError(), BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridBagLayout());

		valid = new JButton("Ok");
		cancel = new JButton("Cancel");
		
		panel.add(cancel);
		panel.add(valid);
		
		p.add(panel, BorderLayout.CENTER);

		return p;
	}

	private void fillListused(boolean includesInterface) {

		linkedlistClasses.removeAll(linkedlistClasses);
		
		Iterator<String> iter = a_classDiagram.getListPackages().keySet()
				.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			A_Package p = (A_Package) a_classDiagram.getListPackages().get(key);
			for (int i = 0; i < p.getListClass().size(); i++) {
				linkedlistClasses.add(p.getListClass().get(i));
			}

			if (includesInterface) {
				for (int k = 0; k < p.getListInterfaces().size(); k++) {
					linkedlistClasses.add(p.getListClass().get(k));
				}
			}
		}
	}

	// ----------------------------------------- //
	// -----------------GETTER/SETTER----------- //
	// ----------------------------------------- //
	
	public JButton getValid() {
		return valid;
	}

	public void setValid(JButton valid) {
		this.valid = valid;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}

	public JList<Object> getListClasses() {
		return listClasses;
	}

	public void setListClasses(JList<Object> listClasses) {
		this.listClasses = listClasses;
	}

	public JPanel getPanelList() {
		return panelList;
	}

	public void setPanelList(JPanel panelList) {
		this.panelList = panelList;
	}

	public A_Class_Diagram getA_classDiagram() {
		return a_classDiagram;
	}

	public void setA_classDiagram(A_Class_Diagram a_classDiagram) {
		this.a_classDiagram = a_classDiagram;
	}

	public GlobalContainer getcGlobal() {
		return cGlobal;
	}

	public void setcGlobal(GlobalContainer cGlobal) {
		this.cGlobal = cGlobal;
	}

	public JCheckBox getIsAssociative() {
		return isAssociative;
	}

	public void setIsAssociative(JCheckBox isAssociative) {
		this.isAssociative = isAssociative;
	}

	public JComboBox<Object> getComboAssociativeWith() {
		return comboAssociativeWith;
	}

	public void setComboAssociativeWith(JComboBox<Object> comboAssociativeWith) {
		this.comboAssociativeWith = comboAssociativeWith;
	}

	public List<com.github.tomap.modeler.model.diagramClass.type.Type> getLinkedlistClasses() {
		return linkedlistClasses;
	}

	public void setLinkedlistClasses(
			List<com.github.tomap.modeler.model.diagramClass.type.Type> linkedlistClasses) {
		this.linkedlistClasses = linkedlistClasses;
	}

	public JTextArea getAreaError() {
		return areaError;
	}

	public void setAreaError(JTextArea areaError) {
		this.areaError = areaError;
	}
	
	
}


