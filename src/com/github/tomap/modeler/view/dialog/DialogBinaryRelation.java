package com.github.tomap.modeler.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.tomap.modeler.view.GlobalContainer;
import com.github.tomap.modeler.controler.ListenerDialogBinaryRelation;
import com.github.tomap.modeler.model.diagramClass.A_Class_Diagram;
import com.github.tomap.modeler.model.diagramClass.aclass.A_Class;
import com.github.tomap.modeler.model.diagramClass.aninterface.An_Interface;
import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.model.diagramClass.excpetion.BadTypeException;
import com.github.tomap.modeler.model.diagramClass.multiplicity.DoubleMultiplicity;
import com.github.tomap.modeler.model.diagramClass.multiplicity.Multiplicity;
import com.github.tomap.modeler.model.diagramClass.relation.Agregation;
import com.github.tomap.modeler.model.diagramClass.relation.Association;
import com.github.tomap.modeler.model.diagramClass.relation.BinaryRelation;
import com.github.tomap.modeler.model.diagramClass.relation.Composition;
import com.github.tomap.modeler.model.diagramClass.relation.Generalization;
import com.github.tomap.modeler.model.diagramClass.relation.Implementation;
import com.github.tomap.modeler.model.diagramClass.relation.Relation;
import com.github.tomap.modeler.model.diagramClass.relation.SimpleRelation;

public class DialogBinaryRelation extends JDialog {

	/**
	 * <h4>DialogRelation Allows to display a dialog to make relation</h4>
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

	
    private A_Class_Diagram a_classDiagram;
    private GlobalContainer cGlobal;
    
    private JPanel panelRelationType;
    private JPanel panelBinaryRelation;
    private JPanel panelMultiplicityfromBR;
    private JPanel panelMultiplicitytoBR;
    private JPanel panelAssociativeClass;
    private JPanel panelValidation;
    private JPanel panelError;
    
    private JComboBox<Relation> comboTypeRelation;
    private JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> comboTypeFrom;
    private JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> comboTypeTo;
    private JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> comboAssociativeWith;
    private JButton valid;
	private JButton cancel;
	private JTextField multFromMin;
	private JTextField multFromMax;
	private JTextField nameAttributeFrom;
	private JTextField multtoMin;
	private JTextField multtoMax;
	private JTextField nameAttributeTo;
	private JTextField relationName;
	private JCheckBox isAssociative;
	private JTextArea areaError;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */
    public DialogBinaryRelation(GlobalContainer cGlobal) {
		super(cGlobal.getMainframe());
		this.cGlobal = cGlobal;
		this.a_classDiagram = cGlobal.getContainerTabbedPane().getDiagram();
		this.setContentPane(createPanels());
		this.pack();
		this.setTitle("Make a relation");
		this.setLocationRelativeTo(cGlobal.getMainframe());
		
		@SuppressWarnings("unused")
		ListenerDialogBinaryRelation listener = new ListenerDialogBinaryRelation(this);
}

    // ----------------------------------------- //
 	// -----------------METHODS----------------- //
 	// ----------------------------------------- //
	private JPanel createPanels() {
		
		JPanel panel = new JPanel(new GridLayout(5, 1));
		
		panel.add(makePanelTypeRelation());
		panel.add(makePanelBinaryRelation());
		panel.add(makePanelAssociativeClass());
		panel.add(makePanelError());
		panel.add(makePanelValidation());
		
		return panel;
	}
	
	private Component makePanelError() {
		panelError = new JPanel();
		areaError = new JTextArea(5, 50);
		areaError.setEditable(false);
		areaError.setForeground(Color.red);
		panelError.add(areaError);
		
		return panelError;
	}

	private JPanel makePanelTypeRelation(){
		
		panelRelationType = new JPanel();
		
		JLabel l = new JLabel("Relation");
		comboTypeRelation = new JComboBox<Relation>(); 
		comboTypeRelation.addItem(new SimpleRelation());
		comboTypeRelation.addItem(new Composition());
		comboTypeRelation.addItem(new Agregation());
		comboTypeRelation.addItem(new Generalization());
		comboTypeRelation.addItem(new Implementation());
		
		
		comboTypeRelation.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				int index = comboTypeRelation.getSelectedIndex();
				System.out.println(index);
				if (index == 0 || index == 1 || index == 2){
					panelBinaryRelation.setVisible(true);
					panelMultiplicityfromBR.setVisible(true);
					panelMultiplicitytoBR.setVisible(true);
					panelAssociativeClass.setVisible(true);
					relationName.setVisible(true);
				}else if (index == 3 || index == 4){
					panelBinaryRelation.setVisible(true);
					panelMultiplicityfromBR.setVisible(false);
					panelMultiplicitytoBR.setVisible(false);
					panelAssociativeClass.setVisible(false);
					relationName.setVisible(false);
				}else if (index == 5){
					panelBinaryRelation.setVisible(false);
					panelMultiplicityfromBR.setVisible(false);
					panelMultiplicitytoBR.setVisible(false);
					panelAssociativeClass.setVisible(true);
				}
				
			}
		});
		
		
		panelRelationType.add(l);
		panelRelationType.add(comboTypeRelation);
		
		return panelRelationType;
		
	}
	
	private JPanel makePanelBinaryRelation(){
		
		//-------------------- FROM 
		JPanel p1 = new JPanel();
		JLabel from = new JLabel("From");
		comboTypeFrom = makeJcomboboxWithModel(true);
		
		p1.add(from);
		p1.add(comboTypeFrom);
		
		panelMultiplicityfromBR = new JPanel();
		JLabel mult1From = new JLabel("Multiplicity");
		multFromMin = new JTextField(3);
		JLabel dash1 = new JLabel("-");
		multFromMax = new JTextField(3);
		JLabel nameA = new JLabel("name");
		nameAttributeFrom = new JTextField(10);
		
		panelMultiplicityfromBR.add(mult1From);
		panelMultiplicityfromBR.add(multFromMin);
		panelMultiplicityfromBR.add(dash1);
		panelMultiplicityfromBR.add(multFromMax);
		panelMultiplicityfromBR.add(nameA);
		panelMultiplicityfromBR.add(nameAttributeFrom);
		
		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(p1, BorderLayout.NORTH);
		p2.add(panelMultiplicityfromBR, BorderLayout.SOUTH);
		p2.setBackground(new Color(218, 224, 241));
		p2.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		
		//-------------------- TO
		JPanel p3 = new JPanel();
		JLabel to = new JLabel("To");
		comboTypeTo = makeJcomboboxWithModel(true);
		// pour chaque package add interface et classe
		p3.add(to);
		p3.add(comboTypeTo);
		
		panelMultiplicitytoBR = new JPanel();
		JLabel mult1To = new JLabel("Multiplicity");
		multtoMin = new JTextField(3);
		JLabel dash2 = new JLabel("-");
		multtoMax = new JTextField(3);
		JLabel nameAA = new JLabel("name");
		nameAttributeTo = new JTextField(10);
		
		panelMultiplicitytoBR.add(mult1To);
		panelMultiplicitytoBR.add(multtoMin);
		panelMultiplicitytoBR.add(dash2);
		panelMultiplicitytoBR.add(multtoMax);
		panelMultiplicitytoBR.add(nameAA);
		panelMultiplicitytoBR.add(nameAttributeTo);
		
		JPanel p4 = new JPanel(new BorderLayout());
		p4.add(p3, BorderLayout.NORTH);
		p4.add(panelMultiplicitytoBR, BorderLayout.SOUTH);
		p4.setBackground(new Color(218, 224, 241));
		p4.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		
		JPanel p5 = new JPanel();
		JLabel arrowsdeb = new JLabel("---");
		relationName = new JTextField(10);
		JLabel arrowsfin = new JLabel("-->");
		p5.add(arrowsdeb);
		p5.add(relationName);
		p5.add(arrowsfin);
		
		panelBinaryRelation = new JPanel(new BorderLayout());
		panelBinaryRelation.add(p2, BorderLayout.WEST);
		panelBinaryRelation.add(p5, BorderLayout.CENTER);
		panelBinaryRelation.add(p4, BorderLayout.EAST);
		panelBinaryRelation.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		return panelBinaryRelation;
		
	}
	
	private JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> makeJcomboboxWithModel(boolean includesInterface){
		
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
	
	private JPanel makePanelAssociativeClass(){
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
	
	private JPanel makePanelValidation(){
		
		panelValidation = new JPanel();
		valid = new JButton("OK");
		cancel = new JButton("Cancel");
		panelValidation.add(cancel);
		panelValidation.add(valid);
		
		return panelValidation;
	}
	
	public void makeRelation() {
		int index = comboTypeRelation.getSelectedIndex();
		switch(index){
		case 0: makeSimpleRelation(); break;
		case 1: makeComposition(); break;
		case 2: makeAgregation(); break;
		case 3: makeGeneralization(); break;
		case 4:makeImplementation(); break;
		}
	}

	private void makeImplementation() {
		
		try{
			A_Class c = (A_Class)comboTypeFrom.getSelectedItem();
			An_Interface i = (An_Interface)comboTypeTo.getSelectedItem();
			
			Implementation im = new Implementation(c,i);
			
			cGlobal.getContainerTabbedPane().getPanelClass().addRelation(im);
			DialogBinaryRelation.this.dispose();	
		}catch(ClassCastException e){
			areaError.setText(e.getMessage());
		}	
	}

	private void makeGeneralization() {
		
		try {
			Generalization g = new Generalization((com.github.tomap.modeler.model.diagramClass.type.Type)comboTypeFrom.getSelectedItem(), 
					(com.github.tomap.modeler.model.diagramClass.type.Type)comboTypeTo.getSelectedItem());
			cGlobal.getContainerTabbedPane().getPanelClass().addRelation(g);
			DialogBinaryRelation.this.dispose();	
		} catch (BadTypeException e) {
			areaError.setText(e.getMessage());
		}
	}
	
	private void makeAgregation() {
		
		Agregation a = new Agregation(relationName.getText());
		makeBinaryRelation(a);
		
		
	}
	
	private void makeComposition() {
		Composition c = new Composition(relationName.getText());
		makeBinaryRelation(c);
		
	}

	private void makeSimpleRelation() {
		SimpleRelation s = new SimpleRelation(relationName.getText());
		makeBinaryRelation(s);
	}
	
	private void makeBinaryRelation(BinaryRelation r){
		try{
			int valminfrom = getFirstMultiplicity(multFromMin.getText()); 
			int valmaxfrom = getSecondMultiplicity(multFromMax.getText());
			
			DoubleMultiplicity from = new DoubleMultiplicity(
					valminfrom,
					valmaxfrom,
					nameAttributeFrom.getText(),
					(A_Class)comboTypeFrom.getSelectedItem(),
					r);
		
			
			int valminto = getFirstMultiplicity(multtoMin.getText()); 
			int valmaxto = getSecondMultiplicity(multtoMax.getText());
			DoubleMultiplicity to = new DoubleMultiplicity(
					valminto, 
					valmaxto,
					nameAttributeTo.getText(),
					(A_Class)comboTypeTo.getSelectedItem(),
					r);
			
			r.updateMultiplicities(from, to);
			
			
			if(isAssociative.isSelected()){
				A_Class associative = (A_Class) comboAssociativeWith.getSelectedItem();
				Association ass = new Association(associative, r);
				cGlobal.getContainerTabbedPane().getPanelClass().addRelation(ass);
			}else{
				cGlobal.getContainerTabbedPane().getPanelClass().addRelation(r);
			}
			
			DialogBinaryRelation.this.dispose();
		}catch(Exception e){
			areaError.setText(e.getMessage());
		}
	}
		
	private int getFirstMultiplicity(String val){
		int valMin = Multiplicity.NO_VALUE;
		
		if (val.equals("*")){
			valMin = Multiplicity.VALUE_MAX;
		}
		else{
			valMin = Integer.parseInt(val);
		}
		
		return valMin;
	}
	
	private int getSecondMultiplicity(String val){
		int valMax = Multiplicity.NO_VALUE;
		
		if (!val.isEmpty()){
			valMax = getFirstMultiplicity(val);
		}
		
		return valMax;
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
	
	

	
}
