package com.github.tomap.modeler.view.dialog;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.tomap.modeler.controler.ListenerDialogClass;
import com.github.tomap.modeler.view.GlobalContainer;

public class DialogClass extends JDialog {

	/**
	 * <h4>DialogAttributeClass Allows to display a dialog to make a class</h4>
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

	private GlobalContainer cGlobal;
	private JCheckBox cb_final;
	private JCheckBox cb_static;
	private JCheckBox cb_abstract;
	private JTextField textClassName;
	private JTextField textPackageName;
	private JButton valid;
	private JButton cancel;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */

	public DialogClass(GlobalContainer cGlobal) {
		super(cGlobal.getMainframe());
		this.cGlobal = cGlobal;
		this.setContentPane(createPanel());
		this.pack();
		this.setTitle("Make a class");
		this.setLocationRelativeTo(cGlobal.getMainframe());
		this.setSize(new Dimension(300, 200));
		
		@SuppressWarnings("unused")
		ListenerDialogClass listener = new ListenerDialogClass(this);

	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	private JPanel createPanel() {

		JPanel content = new JPanel();

		JPanel pn1 = new JPanel();
		cb_final = new JCheckBox("Final");
		pn1.add(cb_final);
		cb_static = new JCheckBox("Static");
		pn1.add(cb_static);
		cb_abstract = new JCheckBox("Abstract");
		pn1.add(cb_abstract);

		JPanel pn2 = new JPanel();
		JLabel l1 = new JLabel("class name");
		textClassName = new JTextField(10);
		pn2.add(l1);
		pn2.add(textClassName);

		JPanel pn4 = new JPanel();
		JLabel l2 = new JLabel("package's name");
		textPackageName = new JTextField(10);
		pn4.add(l2);
		pn4.add(textPackageName);

		JPanel pn3 = new JPanel();
		valid = new JButton("Ok");
		cancel = new JButton("Cancel");
		
		pn3.add(valid);
		pn3.add(cancel);

		content.add(pn4);
		content.add(pn2);
		content.add(pn1);
		content.add(pn3);

		return content;
	}

	public void resetDialog() {
		this.cb_abstract.setSelected(false);
		this.cb_final.setSelected(false);
		this.cb_static.setSelected(false);
		this.textClassName.setText("");
		this.textPackageName.setText("");
	}

	public GlobalContainer getcGlobal() {
		return cGlobal;
	}

	public void setcGlobal(GlobalContainer cGlobal) {
		this.cGlobal = cGlobal;
	}

	public JCheckBox getCb_final() {
		return cb_final;
	}

	public void setCb_final(JCheckBox cb_final) {
		this.cb_final = cb_final;
	}

	public JCheckBox getCb_static() {
		return cb_static;
	}

	public void setCb_static(JCheckBox cb_static) {
		this.cb_static = cb_static;
	}

	public JCheckBox getCb_abstract() {
		return cb_abstract;
	}

	public void setCb_abstract(JCheckBox cb_abstract) {
		this.cb_abstract = cb_abstract;
	}

	public JTextField getTextClassName() {
		return textClassName;
	}

	public void setTextClassName(JTextField textClassName) {
		this.textClassName = textClassName;
	}

	public JTextField getTextPackageName() {
		return textPackageName;
	}

	public void setTextPackageName(JTextField textPackageName) {
		this.textPackageName = textPackageName;
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
