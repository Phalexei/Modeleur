package com.github.tomap.modeler.view.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.tomap.modeler.view.GlobalContainer;

public class DialogInterface extends JDialog {

	/**
	 * <h4>DialogInterface Allows to display a dialog to make an Interface</h4>
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
	private JTextField textInterfaceName;
	private JTextField textPackageName;
	private JButton valid;
	private JButton cancel;

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Construcutor
	 */

	public DialogInterface(GlobalContainer cGlobal) {
		super(cGlobal.getMainframe());
		this.cGlobal = cGlobal;
		this.setContentPane(createPanel());
		this.pack();
		this.setTitle("Make a interface");
		this.setLocationRelativeTo(cGlobal.getMainframe());
		this.setSize(new Dimension(300, 200));

	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	private JPanel createPanel() {

		JPanel content = new JPanel();

		JPanel pn2 = new JPanel();
		JLabel l1 = new JLabel("interface's name");
		textInterfaceName = new JTextField(10);
		pn2.add(l1);
		pn2.add(textInterfaceName);

		JPanel pn4 = new JPanel();
		JLabel l2 = new JLabel("package's name");
		textPackageName = new JTextField(10);
		pn4.add(l2);
		pn4.add(textPackageName);

		JPanel pn3 = new JPanel();
		valid = new JButton("Ok");
		valid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String packagename = textPackageName.getText();
				String classname = textInterfaceName.getText();

				cGlobal.getContainerTabbedPane().getPanelClass()
						.addInterface(packagename, classname);

				resetDialog();
				setVisible(false);

			}
		});

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resetDialog();
				setVisible(false);
			}
		});
		pn3.add(valid);
		pn3.add(cancel);

		content.add(pn4);
		content.add(pn2);
		content.add(pn3);

		return content;
	}

	private void resetDialog() {
		this.textInterfaceName.setText("");
		this.textPackageName.setText("");
	}

}
