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
import com.github.tomap.modeler.model.diagramClass.apackage.A_Package;
import com.github.tomap.modeler.model.diagramClass.relation.Agregation;
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

	public JPanel getPanelRelationType() {
		return panelRelationType;
	}

	public void setPanelRelationType(JPanel panelRelationType) {
		this.panelRelationType = panelRelationType;
	}

	public JPanel getPanelBinaryRelation() {
		return panelBinaryRelation;
	}

	public void setPanelBinaryRelation(JPanel panelBinaryRelation) {
		this.panelBinaryRelation = panelBinaryRelation;
	}

	public JPanel getPanelMultiplicityfromBR() {
		return panelMultiplicityfromBR;
	}

	public void setPanelMultiplicityfromBR(JPanel panelMultiplicityfromBR) {
		this.panelMultiplicityfromBR = panelMultiplicityfromBR;
	}

	public JPanel getPanelMultiplicitytoBR() {
		return panelMultiplicitytoBR;
	}

	public void setPanelMultiplicitytoBR(JPanel panelMultiplicitytoBR) {
		this.panelMultiplicitytoBR = panelMultiplicitytoBR;
	}

	public JPanel getPanelAssociativeClass() {
		return panelAssociativeClass;
	}

	public void setPanelAssociativeClass(JPanel panelAssociativeClass) {
		this.panelAssociativeClass = panelAssociativeClass;
	}

	public JPanel getPanelValidation() {
		return panelValidation;
	}

	public void setPanelValidation(JPanel panelValidation) {
		this.panelValidation = panelValidation;
	}

	public JPanel getPanelError() {
		return panelError;
	}

	public void setPanelError(JPanel panelError) {
		this.panelError = panelError;
	}

	public JComboBox<Relation> getComboTypeRelation() {
		return comboTypeRelation;
	}

	public void setComboTypeRelation(JComboBox<Relation> comboTypeRelation) {
		this.comboTypeRelation = comboTypeRelation;
	}

	public JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> getComboTypeFrom() {
		return comboTypeFrom;
	}

	public void setComboTypeFrom(
			JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> comboTypeFrom) {
		this.comboTypeFrom = comboTypeFrom;
	}

	public JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> getComboTypeTo() {
		return comboTypeTo;
	}

	public void setComboTypeTo(
			JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> comboTypeTo) {
		this.comboTypeTo = comboTypeTo;
	}

	public JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> getComboAssociativeWith() {
		return comboAssociativeWith;
	}

	public void setComboAssociativeWith(
			JComboBox<com.github.tomap.modeler.model.diagramClass.type.Type> comboAssociativeWith) {
		this.comboAssociativeWith = comboAssociativeWith;
	}

	public JTextField getMultFromMin() {
		return multFromMin;
	}

	public void setMultFromMin(JTextField multFromMin) {
		this.multFromMin = multFromMin;
	}

	public JTextField getMultFromMax() {
		return multFromMax;
	}

	public void setMultFromMax(JTextField multFromMax) {
		this.multFromMax = multFromMax;
	}

	public JTextField getNameAttributeFrom() {
		return nameAttributeFrom;
	}

	public void setNameAttributeFrom(JTextField nameAttributeFrom) {
		this.nameAttributeFrom = nameAttributeFrom;
	}

	public JTextField getMulttoMin() {
		return multtoMin;
	}

	public void setMulttoMin(JTextField multtoMin) {
		this.multtoMin = multtoMin;
	}

	public JTextField getMulttoMax() {
		return multtoMax;
	}

	public void setMulttoMax(JTextField multtoMax) {
		this.multtoMax = multtoMax;
	}

	public JTextField getNameAttributeTo() {
		return nameAttributeTo;
	}

	public void setNameAttributeTo(JTextField nameAttributeTo) {
		this.nameAttributeTo = nameAttributeTo;
	}

	public JTextField getRelationName() {
		return relationName;
	}

	public void setRelationName(JTextField relationName) {
		this.relationName = relationName;
	}

	public JCheckBox getIsAssociative() {
		return isAssociative;
	}

	public void setIsAssociative(JCheckBox isAssociative) {
		this.isAssociative = isAssociative;
	}

	public JTextArea getAreaError() {
		return areaError;
	}

	public void setAreaError(JTextArea areaError) {
		this.areaError = areaError;
	}
	
	

	
}
