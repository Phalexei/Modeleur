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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.github.tomap.modeler.controler.ListenerDialogAttribute;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.type.AString;
import com.github.tomap.modeler.model.diagramClass.type.Boolean;
import com.github.tomap.modeler.model.diagramClass.type.Double;
import com.github.tomap.modeler.model.diagramClass.type.Float;
import com.github.tomap.modeler.model.diagramClass.type.Integer;
import com.github.tomap.modeler.model.diagramClass.typedElement.Attribute;
import com.github.tomap.modeler.model.diagramClass.visibility.Package;
import com.github.tomap.modeler.model.diagramClass.visibility.Private;
import com.github.tomap.modeler.model.diagramClass.visibility.Protected;
import com.github.tomap.modeler.model.diagramClass.visibility.Public;
import com.github.tomap.modeler.model.diagramClass.visibility.Visibility;
import com.github.tomap.modeler.view.DClass.PanelAttibuteClass;

public class DialogAttributeClass extends JDialog{

	/**
	 * <h4>DialogAttributeClass Allows to display a dialog for attributs </h4>
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
    private JButton addButton;
    private JButton removeButton;
	private JTable tableAttribute;
    private AttributeClassTableModel attributeModel;
    private A_Class a_class;
    private DefaultListModel<Attribute> modelJlist;
    
    // ----------------------------------------- //
 	// --------------CONSTRUCTOR---------------- //
 	// ----------------------------------------- //
 	/**
 	 * Construcutor
 	 */
	public DialogAttributeClass(PanelAttibuteClass pAttribute){
		
		this.a_class = pAttribute.getA_class();
		this.modelJlist = pAttribute.getModel();
		this.removeButton = pAttribute.getRemoveButton();
		
		tableAttribute = CreateAttributeTable();
		JScrollPane CompTableScrollpane = new JScrollPane(tableAttribute, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel bottomPanel = CreateBottomPanel();
		
        this.add(CompTableScrollpane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
		
        this.pack();
		this.setTitle("Make Attributes");
		this.setLocationRelativeTo(null);
		
		@SuppressWarnings("unused")
		ListenerDialogAttribute listener = new ListenerDialogAttribute(this);
		
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	private JTable CreateAttributeTable() {
        attributeModel = new AttributeClassTableModel();
        attributeModel.addRow();
        
        JTable table = new JTable (attributeModel);
        table.setRowHeight(new AttributeCellPanel().getPreferredSize().height);
        
        AttributeCellEditorRenderer compCellEditorRenderer = new AttributeCellEditorRenderer();
        table.setDefaultRenderer(Object.class, compCellEditorRenderer);
        table.setDefaultEditor(Object.class, compCellEditorRenderer);
        return table;
    }

    private JPanel CreateBottomPanel() {
        addButton = new JButton("Add Attribute");
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Object source = ae.getSource();

                if (source == addButton) {
                    attributeModel.addRow();
                    attributeModel.fireTableDataChanged();
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
    public void setVisible(boolean b){
    	super.setVisible(b);
    	resetDialog();
    }
    
    public void resetDialog(){
    	
    	attributeModel = new AttributeClassTableModel();
    	attributeModel.addColumn("Attribute (visibility, derivated, final, static, type, name)");
    	tableAttribute.setModel(attributeModel);
        attributeModel.addRow();
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

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(JButton removeButton) {
		this.removeButton = removeButton;
	}

	public JTable getTableAttribute() {
		return tableAttribute;
	}

	public void setTableAttribute(JTable tableAttribute) {
		this.tableAttribute = tableAttribute;
	}

	public AttributeClassTableModel getAttributeModel() {
		return attributeModel;
	}

	public void setAttributeModel(AttributeClassTableModel attributeModel) {
		this.attributeModel = attributeModel;
	}

	public A_Class getA_class() {
		return a_class;
	}

	public void setA_class(A_Class a_class) {
		this.a_class = a_class;
	}

	public DefaultListModel<Attribute> getModelJlist() {
		return modelJlist;
	}

	public void setModelJlist(DefaultListModel<Attribute> modelJlist) {
		this.modelJlist = modelJlist;
	}
    
	private class AttributeCellEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

	    private static final long serialVersionUID = 1L;
	    private AttributeCellPanel renderer = new AttributeCellPanel();
	    private AttributeCellPanel editor = new AttributeCellPanel();
	    
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        renderer.setAttribute((Attribute) value);
	        return renderer;
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	        editor.setAttribute((Attribute) value);
	        return editor;
	    }

	    @Override
	    public Object getCellEditorValue() {
	      
			return editor.getAttribute();
			
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
    
	public class AttributeClassTableModel extends DefaultTableModel {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public int getColumnCount() {
	        return 1;
	    }

	    public void addRow() {
	        super.addRow(new Object[]{
	        		new Attribute("", false, new Integer(), false, false, new Public())
	        });
	        super.fireTableDataChanged();
	        
	    }
	    
	}
  
	class AttributeCellPanel extends JPanel {

	    private static final long serialVersionUID = 1L;
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    
	    private JComboBox<Type> visibilityCombo = new JComboBox(
				new Object[]{new Public(), new Private(), new Protected(), new Package()});
	    
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private JComboBox<Type> typeCombo = new JComboBox(
				new Object[]{new Integer(), new Double(), new Float(), new Boolean(), new AString()});
	    
		private JCheckBox isderivatedBox = new JCheckBox();
		private JCheckBox isfinalBox = new JCheckBox();
		private JCheckBox isstaticBox = new JCheckBox();
	    private JTextField attributeName = new JTextField();
	    private JButton removeButton = new JButton("remove");

	    
	    AttributeCellPanel() {
	        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	       
	        removeButton.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, (Component) e.getSource());
	                int row = table.getEditingRow();
	                table.getCellEditor().stopCellEditing();
	                ((DefaultTableModel) table.getModel()).removeRow(row);
	            }
	        });
	        
	        add(visibilityCombo);
	        add(isderivatedBox);
	        add(isfinalBox);
	        add(isstaticBox);
	        add(typeCombo);
	        add(attributeName);
	        add(Box.createHorizontalStrut(50));
	        add(removeButton);
	    }
	    
	    public void setAttribute(Attribute a) {
	    	
	    	int index = 0;
	    	
	    	if(a.getVisibility().toString().equals("+")){
	    		index = 0;
	    	}else if(a.getVisibility().toString().equals("-")){
	    		index = 1;
	    	}else if(a.getVisibility().toString().equals("#")){
	    		index = 2;
	    	}else if(a.getVisibility().toString().equals("~")){
	    		index = 3;
	    	}
	    	
	    	visibilityCombo.setSelectedIndex(index);
	    	
	    	if(a.getType().toString().equals("int")){
	    		index = 0;
	    	}else if(a.getType().toString().equals("double")){
	    		index = 1;
	    	}else if(a.getType().toString().equals("float")){
				index = 2;
			}else if(a.getType().toString().equals("boolean")){
				index = 3;
			}else if(a.getType().toString().equals("String")){
				index = 4;
			}
	    	typeCombo.setSelectedIndex(index);
	    	
	    	
	        isfinalBox.setSelected(a.isFinal());
	        isderivatedBox.setSelected(a.isDerivated());
	        isstaticBox.setSelected(a.isStatic());
	        attributeName.setText(a.getName());
	    }

	    public Attribute getAttribute() {
	    	return new Attribute(attributeName.getText(), isfinalBox.isSelected(), 
	    			(com.github.tomap.modeler.model.diagramClass.type.Type)typeCombo.getSelectedItem(),
	    			isstaticBox.isSelected(),  isderivatedBox.isSelected(), (Visibility)visibilityCombo.getSelectedItem());
	    }

	}
	
}

	//----------------------------------------- //
	// -----------------OTHER CLASS------------ //
	// ----------------------------------------- //






