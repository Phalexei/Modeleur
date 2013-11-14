package com.github.tomap.modeler.view.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

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

import com.github.tomap.modeler.view.DClass.PanelMethodClass;
import com.github.tomap.modeler.controler.ListenerDialogMethodClass;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.type.Boolean;
import com.github.tomap.modeler.model.diagramClass.type.AString;
import com.github.tomap.modeler.model.diagramClass.type.Double;
import com.github.tomap.modeler.model.diagramClass.type.Float;
import com.github.tomap.modeler.model.diagramClass.type.Integer;
import com.github.tomap.modeler.model.diagramClass.type.Void;
import com.github.tomap.modeler.model.diagramClass.typedElement.Method;
import com.github.tomap.modeler.model.diagramClass.typedElement.Parameter;
import com.github.tomap.modeler.model.diagramClass.visibility.Package;
import com.github.tomap.modeler.model.diagramClass.visibility.Private;
import com.github.tomap.modeler.model.diagramClass.visibility.Protected;
import com.github.tomap.modeler.model.diagramClass.visibility.Public;
import com.github.tomap.modeler.model.diagramClass.visibility.Visibility;

public class DialogMethodClass extends JDialog {

	/**
	 * <h4>DialogMethodClass Allows to display a dialog to make method for a
	 * class</h4>
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

	private JComboBox<Visibility> comboVisibility;
	private JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> comboReturnType;
	private JCheckBox finalButton;
	private JCheckBox abstractButton;
	private JTextField textMethodName;
	private JButton valid;
	private JButton cancel;
	private JTable tableParameter;
	private ParameterClassTableModel parameterModel;
	private JButton addButton;
	private A_Class a_class;
	private DefaultListModel<Method> modelJlist;
	private JButton removeButton;
	private ButtonGroup group;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */

	public DialogMethodClass(PanelMethodClass pMethod) {

		this.a_class = pMethod.getA_class();
		this.modelJlist = pMethod.getModel();
		this.removeButton = pMethod.getRemoveButton();

		tableParameter = CreateParameterTable();
		JPanel methodPanel = createPanelMethod();
		JScrollPane CompTableScrollpane = new JScrollPane(tableParameter,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel bottomPanel = CreateBottomPanel();

		this.add(methodPanel, BorderLayout.NORTH);
		this.add(CompTableScrollpane, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		this.pack();
		this.setTitle("Make a Method");
		this.setLocationRelativeTo(null);
		
		@SuppressWarnings("unused")
		ListenerDialogMethodClass listener = new ListenerDialogMethodClass(this);

	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //
	private JPanel createPanelMethod() {

		JPanel content = new JPanel();

		// visibility
		JPanel pn2 = new JPanel();
		JLabel l1 = new JLabel("Visibility");
		pn2.add(l1);
		comboVisibility = new JComboBox<Visibility>();
		comboVisibility.addItem(new Private());
		comboVisibility.addItem(new Public());
		comboVisibility.addItem(new Protected());
		comboVisibility.addItem(new Package());
		pn2.add(comboVisibility);

		// return type
		JPanel pn4 = new JPanel();
		JLabel l2 = new JLabel("Return type");
		comboReturnType = new JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type>();
		comboReturnType.addItem(new Integer());
		comboReturnType.addItem(new Double());
		comboReturnType.addItem(new Float());
		comboReturnType.addItem(new Boolean());
		comboReturnType.addItem(new AString());
		comboReturnType.addItem(new Void());
		pn4.add(l2);
		pn4.add(comboReturnType);

		// name
		JPanel pn5 = new JPanel();
		JLabel l3 = new JLabel("name");
		textMethodName = new JTextField(10);
		pn5.add(l3);
		pn5.add(textMethodName);

		// boolean
		JPanel pn6 = new JPanel();
		finalButton = new JCheckBox("Final");
		abstractButton = new JCheckBox("Abstract");
		group = new ButtonGroup();

		group.add(finalButton);
		group.add(abstractButton);
		pn6.add(finalButton);
		pn6.add(abstractButton);

		content.add(pn2);
		content.add(pn4);
		content.add(pn5);
		content.add(pn6);

		return content;
	}

	private JTable CreateParameterTable() {
		parameterModel = new ParameterClassTableModel();
		parameterModel.addRow();

		JTable table = new JTable(parameterModel);
		table.setRowHeight(new ClassParameterCellPanel().getPreferredSize().height);

		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue("Parameters (type, final, name)");
		th.repaint();

		ClassParameterCellEditorRenderer compCellEditorRenderer = new ClassParameterCellEditorRenderer();
		table.setDefaultRenderer(Object.class, compCellEditorRenderer);
		table.setDefaultEditor(Object.class, compCellEditorRenderer);
		return table;
	}

	private JPanel CreateBottomPanel() {
		addButton = new JButton("Add Parameter");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				Object source = ae.getSource();

				if (source == addButton) {
					parameterModel.addRow();
					parameterModel.fireTableDataChanged();
				}
			}
		});

		JPanel panel = new JPanel(new GridBagLayout());
		panel.add(addButton);

		valid = new JButton("Ok");
		cancel = new JButton("Cancel");
		panel.add(cancel);
		panel.add(valid);

		return panel;
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		privateResetAllFields();
	}

	private void privateResetAllFields() {
		comboVisibility.setSelectedIndex(0);
		comboReturnType.setSelectedIndex(0);
		textMethodName.setText("");
		group.clearSelection();

		parameterModel = new ParameterClassTableModel();
		parameterModel.addColumn("Parameter(type, final, name)");
		tableParameter.setModel(parameterModel);
		parameterModel.addRow();
	}
	
	// ----------------------------------------- //
	// -----------------OTHER CLASS------------ //
	// ----------------------------------------- //

	private class ClassParameterCellEditorRenderer extends AbstractCellEditor implements
			TableCellRenderer, TableCellEditor {

		private static final long serialVersionUID = 1L;
		private ClassParameterCellPanel renderer = new ClassParameterCellPanel();
		private ClassParameterCellPanel editor = new ClassParameterCellPanel();

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			renderer.setParameter((Parameter) value);
			return renderer;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			editor.setParameter((Parameter) value);
			return editor;
		}

		@Override
		public Object getCellEditorValue() {
			return editor.getParameter();
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

	public class ParameterClassTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return 1;
		}

		public void addRow() {
			super.addRow(new Object[] { new Parameter("", false, new Integer(),
					null) });
			super.fireTableDataChanged();

		}

	}

	private class ClassParameterCellPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private JComboBox<Type> typeCombo = new JComboBox(new Object[] {
				new Integer(), new Double(), new Float(), new Boolean(),
				new AString() });
		private JCheckBox isfinalBox = new JCheckBox();
		private JTextField paramterName = new JTextField();
		private JButton removeButton = new JButton("remove");

		ClassParameterCellPanel() {
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

			add(typeCombo);
			add(isfinalBox);
			add(paramterName);
			add(Box.createHorizontalStrut(100));
			add(removeButton);
		}

		public void setParameter(Parameter p) {

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

			typeCombo.setSelectedIndex(index);
			isfinalBox.setSelected(p.isFinal());
			paramterName.setText(p.getName());
		}

		public Parameter getParameter() {
			return new Parameter(paramterName.getText(),
					isfinalBox.isSelected(),
					(com.github.tomap.modeler.model.diagramClass.type.Type) typeCombo.getSelectedItem(),
					new Method("m"));
		}

	}

	public JComboBox<Visibility> getComboVisibility() {
		return comboVisibility;
	}

	public void setComboVisibility(JComboBox<Visibility> comboVisibility) {
		this.comboVisibility = comboVisibility;
	}

	public JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> getComboReturnType() {
		return comboReturnType;
	}

	public void setComboReturnType(
			JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> comboReturnType) {
		this.comboReturnType = comboReturnType;
	}

	public JCheckBox getFinalButton() {
		return finalButton;
	}

	public void setFinalButton(JCheckBox finalButton) {
		this.finalButton = finalButton;
	}

	public JCheckBox getAbstractButton() {
		return abstractButton;
	}

	public void setAbstractButton(JCheckBox abstractButton) {
		this.abstractButton = abstractButton;
	}

	public JTextField getTextMethodName() {
		return textMethodName;
	}

	public void setTextMethodName(JTextField textMethodName) {
		this.textMethodName = textMethodName;
	}

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

	public JTable getTableParameter() {
		return tableParameter;
	}

	public void setTableParameter(JTable tableParameter) {
		this.tableParameter = tableParameter;
	}

	public ParameterClassTableModel getParameterModel() {
		return parameterModel;
	}

	public void setParameterModel(ParameterClassTableModel parameterModel) {
		this.parameterModel = parameterModel;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public A_Class getA_class() {
		return a_class;
	}

	public void setA_class(A_Class a_class) {
		this.a_class = a_class;
	}

	public DefaultListModel<Method> getModelJlist() {
		return modelJlist;
	}

	public void setModelJlist(DefaultListModel<Method> modelJlist) {
		this.modelJlist = modelJlist;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(JButton removeButton) {
		this.removeButton = removeButton;
	}

	public ButtonGroup getGroup() {
		return group;
	}

	public void setGroup(ButtonGroup group) {
		this.group = group;
	}

	
}

