package com.github.tomap.modeler.view.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuAbout extends Menu {
	/**
	 * <h4>MenuAbout is an information menu</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //

	private final static String TITLE_ABOUT = "About";
	//private final static String TITLE_HELP = "Help";
	private final static String TITLE_INFORMATION = "About...";

	//private final static Integer F1_KEY = 112;

	// ----------------------------------------- //
	// --------------- ATTRIBUTS --------------- //
	// ----------------------------------------- //

	private JMenuItem help;
	private JMenuItem about;

	// ----------------------------------------- //
	// ------------- CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Constructor
	 */
	public MenuAbout() {
		this.setText(TITLE_ABOUT);
		this.setMnemonic(KeyEvent.VK_A);

		buildItems();
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	/**
	 * make menu's items
	 */
	private void buildItems() {
		/*
		help = buildMenuItem(help, TITLE_HELP,
				KeyStroke.getKeyStroke(F1_KEY, 0), "help.png");
		this.add(help);

		this.addSeparator();
		*/
		about = buildMenuItem(about, TITLE_INFORMATION, "information.png");
		this.add(about);
	}

	// ----------------------------------------- //
	// ---------------GETTERS------------------- //
	// ----------------------------------------- //
	public JMenuItem getHelp() {
		return help;
	}

	public JMenuItem getAbout() {
		return about;
	}

	// ----------------------------------------- //
	// ----------------SETTERS------------------ //
	// ----------------------------------------- //
	public void setHelp(JMenuItem help) {
		this.help = help;
	}

	public void setAbout(JMenuItem about) {
		this.about = about;
	}

}
