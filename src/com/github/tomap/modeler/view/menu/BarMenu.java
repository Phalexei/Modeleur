package com.github.tomap.modeler.view.menu;

import java.awt.Dimension;
import javax.swing.JMenuBar;

import com.github.tomap.modeler.view.MainFrame;

@SuppressWarnings("serial")
public class BarMenu extends JMenuBar {
	/**
	 * <h4>BarMenu allows to manage the menu bar</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTES -------------- //
	// ----------------------------------------- //

	public final static Integer SIZE_X = MainFrame.SIZE_X;
	public final static Integer SIZE_Y = MainFrame.SIZE_Y / 20;

	// ----------------------------------------- //
	// --------------- ATTRIBUTS --------------- //
	// ----------------------------------------- //
	/**
	 * Menu file
	 * 
	 * @see FileMenu
	 */
	private FileMenu fileMenu;
	/**
	 * Menu about
	 * 
	 * @see MenuAbout
	 */
	private MenuAbout aboutMenu;

	// ----------------------------------------- //
	// --------------CONSTRUCTEUR--------------- //
	// ----------------------------------------- //
	/**
	 * Constructor
	 */
	public BarMenu() {
		this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));

		fileMenu = new FileMenu();
		this.add(fileMenu);

		aboutMenu = new MenuAbout();
		this.add(aboutMenu);
	}

	// ----------------------------------------- //
	// ---------------GETTERS------------------- //
	// ----------------------------------------- //

	public FileMenu getFileMenu() {
		return fileMenu;
	}

	public MenuAbout getMenuABout() {
		return aboutMenu;
	}

	// ----------------------------------------- //
	// ----------------SETTERS------------------ //
	// ----------------------------------------- //
	public void setFileMenu(FileMenu fileMenu) {
		this.fileMenu = fileMenu;
	}

	public void setMenuAPropos(MenuAbout menuAPropos) {
		this.aboutMenu = menuAPropos;
	}

}
