package com.github.tomap.modeler.view.menu;

import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class FileMenu extends Menu {
	/**
	 * <h4>FileMenu manages the file menu</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS -------------- //
	// ----------------------------------------- //

	private final static String TITLE_MENU = "File";

	/*private final static String TITLE_NEW = "New";
	private final static String TITLE_OPEN = "Open";
	private final static String TITLE_SAVE = "Save";*/
	private final static String TITLE_EXIT = "Quit";

	// ----------------------------------------- //
	// --------------- ATTRIBUTES --------------- //
	// ----------------------------------------- //

	private JMenuItem newFile;
	private JMenuItem openFile;
	private JMenuItem saveFile;
	private JMenuItem exit;

	// ----------------------------------------- //
	// ------------- CONSTRUCTOR --------------- //
	// ----------------------------------------- //

	/**
	 * Constructeur principal de la classe MenuFichier
	 */
	public FileMenu() {
		this.setText(TITLE_MENU);
		this.setMnemonic(KeyEvent.VK_F);

		buildItems();
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	/**
	 * Methode de creation d'items appartenant au menu Fichier
	 */
	private void buildItems() {
		/*
		newFile = buildMenuItem(newFile, TITLE_NEW,
				KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK),
				"folder_add.png");
		this.add(newFile);

		openFile = buildMenuItem(openFile, TITLE_OPEN,
				KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK),
				"folder_add.png");
		this.add(openFile);

		this.addSeparator();

		saveFile = buildMenuItem(saveFile, TITLE_SAVE,
				KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK),
				"save_as.png");
		this.add(saveFile);

		this.addSeparator();
	*/
		exit = buildMenuItem(exit, TITLE_EXIT,
				KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK), "");
		this.add(exit);
	}

	// ----------------------------------------- //
	// ----------------GETTERS------------------ //
	// ----------------------------------------- //

	public JMenuItem getNewFile() {
		return newFile;
	}

	public JMenuItem getOpenFile() {
		return openFile;
	}

	public JMenuItem getSaveFile() {
		return saveFile;
	}

	public JMenuItem getExit() {
		return exit;
	}

	// ----------------------------------------- //
	// -----------------SETTERS----------------- //
	// ----------------------------------------- //

	public void setNewFile(JMenuItem newFile) {
		this.newFile = newFile;
	}

	public void setOpenFile(JMenuItem openFile) {
		this.openFile = openFile;
	}

	public void setSaveFile(JMenuItem saveFile) {
		this.saveFile = saveFile;
	}

	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}
}
