package com.github.tomap.modeler.view.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EventObject;
import java.util.Iterator;

import javax.swing.AbstractCellEditor;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.github.tomap.modeler.view.GlobalContainer;
import com.github.tomap.modeler.view.DClass.PanelMethodClass;
import com.github.tomap.modeler.model.diagramClass.A_Class_Diagram;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.model.diagramClass.multiplicity.DoubleMultiplicity;
import com.github.tomap.modeler.model.diagramClass.relation.N_Relation;
import com.github.tomap.modeler.model.diagramClass.type.Type;

public class DialogNrelation extends JDialog {

	/**
	 * <h4>DialogMethodClass Allows to display a dialog to make N Relation</h4>
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
	private JTable tableNrelation;
	private NRelationTableModel nRelationModel;
	private JButton addButton;
	private A_Class a_class;
	private JButton removeButton;

	private JPanel panelAssociativeClass;

	private A_Class_Diagram a_classDiagram;
	private GlobalContainer cGlobal;

	private JCheckBox isAssociative;
	private JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> comboAssociativeWith;

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
		this.setTitle("Make a Method");
		this.setLocationRelativeTo(null);

	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	private JPanel createPanels() {

		JPanel panel = new JPanel(new BorderLayout());

		panel.add(makePanelTable(),BorderLayout.NORTH);
		panel.add(makePanelAssociativeClass(),BorderLayout.CENTER);
		panel.add(makePanelValidation(),BorderLayout.SOUTH);

		return panel;
	}

	private Component makePanelTable() {
		
		tableNrelation = CreateNRelationTable();
		JScrollPane CompTableScrollpane = new JScrollPane(tableNrelation,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		return CompTableScrollpane;
	}

	private JPanel makePanelAssociativeClass() {
		panelAssociativeClass = new JPanel();
		isAssociative = new JCheckBox("Associative", false);

		comboAssociativeWith = makeJcomboboxWithModel(false);
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
	
	private Component makePanelValidation() {
		addButton = new JButton("Add Parameter");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				Object source = ae.getSource();

				if (source == addButton) {
					nRelationModel.addRow();
					nRelationModel.fireTableDataChanged();
				}
			}
		});

		JPanel panel = new JPanel(new GridBagLayout());
		panel.add(addButton);

		valid = new JButton("Ok");
		valid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				Visibility v = (Visibility) comboVisibility.getSelectedItem();
				com.github.tomap.modeler.model.diagramClass.type.Type returnType = (com.github.tomap.modeler.model.diagramClass.type.Type) comboReturnType
						.getSelectedItem();
				String name = textMethodName.getText();
				boolean isFinal = finalButton.isSelected();
				boolean isAbstract = abstractButton.isSelected();

				Method m = new Method(v, returnType, name, isFinal, isAbstract);

				for (int i = 0; i < parameterModel.getRowCount(); i++) {
					for (int j = 0; j < parameterModel.getColumnCount(); j++) {

						Parameter p = (Parameter) parameterModel.getValueAt(i,
								j);
						m.addParameter(p);

					}
				}
				m.setBelongtoType(a_class);
				a_class.addMethod(m);
				modelJlist.addElement(m);
				removeButton.setEnabled(true);
				setVisible(false);
				*/
				System.out.println("okk");
			}
		});

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
			}
		});
		panel.add(cancel);
		panel.add(valid);

		return panel;
	}
	
	public JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> makeJcomboboxWithModel(boolean includesInterface){
			
			JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> j = new JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type>();
			
			Iterator<String> iter = a_classDiagram.getListPackages().keySet().iterator(); 
			while(iter.hasNext()) { 
				String key = (String)iter.next(); 
				A_Package p = (A_Package)a_classDiagram.getListPackages().get(key); 
				
				for (int i = 0; i < p.getListClass().size(); i++){
					j.addItem(p.getListClass().get(i));
				}
				
				if(includesInterface){
					for (int k = 0; k < p.getListInterfaces().size(); k++){
						j.addItem(p.getListInterfaces().get(k));
					}
				}
			}
			
			return j;
			
	}

	private JTable CreateNRelationTable() {
		nRelationModel = new NRelationTableModel();
		nRelationModel.addRow();

		JTable table = new JTable(nRelationModel);
		table.setRowHeight(new NRelationCellPanel(makeJcomboboxWithModel(false)).getPreferredSize().height);

		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue("Relation (associated class, mult1, mult2, name)");
		th.repaint();

		NRelationCellEditorRenderer compCellEditorRenderer = new NRelationCellEditorRenderer(makeJcomboboxWithModel(false));
		table.setDefaultRenderer(Object.class, compCellEditorRenderer);
		table.setDefaultEditor(Object.class, compCellEditorRenderer);
		return table;
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		privateResetAllFields();
	}

	private void privateResetAllFields() {
		
		nRelationModel = new NRelationTableModel();
		tableNrelation.setModel(nRelationModel);
		nRelationModel.addRow();
		
		isAssociative.setSelected(false);
	}

}

// ----------------------------------------- //
// -----------------OTHER CLASS------------ //
// ----------------------------------------- //

class NRelationCellEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

	private static final long serialVersionUID = 1L;
	private NRelationCellPanel renderer;
	private NRelationCellPanel editor;

	public NRelationCellEditorRenderer(JComboBox<Type> j){
		renderer = new NRelationCellPanel(j);
		editor = new NRelationCellPanel(j);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		renderer.setMultplicity((DoubleMultiplicity) value);
		return renderer;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		editor.setMultplicity((DoubleMultiplicity) value);
		return editor;
	}

	@Override
	public Object getCellEditorValue() {
		return editor.getMultiplicity();
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		return false;
	}
}

class NRelationTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	@Override
	public int getColumnCount() {
		return 1;
	}

	public void addRow() {
		super.addRow(new Object[] { new DoubleMultiplicity(0, 0, "", null, new N_Relation("")) });
		super.fireTableDataChanged();

	}

}

class NRelationCellPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox<Type> nRelationCombo = new JComboBox<Type>();
	
	private JTextField multFrom = new JTextField();
	private JTextField multTo = new JTextField();
	private JTextField attributeName = new JTextField();
	private JButton removeButton = new JButton("remove");

	NRelationCellPanel(JComboBox<Type> j) {
		nRelationCombo = j;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) SwingUtilities.getAncestorOfClass(
						JTable.class, (Component) e.getSource());
				int row = table.getEditingRow();
				table.getCellEditor().stopCellEditing();
				((DefaultTableModel) table.getModel()).removeRow(row);
			}
		});

		add(nRelationCombo);
		add(multFrom);
		add(multTo);
		add(attributeName);
		add(Box.createHorizontalStrut(100));
		add(removeButton);
	}

	public void setMultplicity(DoubleMultiplicity m) {

		/*
		int index = 0;
		if (p.getType().toString().equals("int")) {
			index = 0;
		} else if (p.getType().toString().equals("double")) {
			index = 1;
		} else if (p.getType().toString().equals("float")) {
			index = 2;
		} else if (p.getType().toString().equals("boolean")) {
			index = 3;
		} else if (p.getType().toString().equals("String")) {
			index = 4;
		}
		*/

		nRelationCombo.setSelectedIndex(0);
		multFrom.setText(String.valueOf(m.getValue()));
		multTo.setText(String.valueOf(m.getMaxValue()));
		attributeName.setText(m.getAttributeName());
	}

	public DoubleMultiplicity getMultiplicity() {
		
		int multfrom  = Integer.parseInt(multFrom.getText());
		int multo = Integer.parseInt(multTo.getText());
		
		return new DoubleMultiplicity(multfrom,multo, attributeName.getText(), (A_Class)nRelationCombo.getSelectedItem(), new N_Relation(""));
	}
	
	

}
