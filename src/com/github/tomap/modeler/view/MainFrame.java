package com.github.tomap.modeler.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.tomap.modeler.controler.ListenerBarMenu;
import com.github.tomap.modeler.main.Main;
import com.github.tomap.modeler.view.menu.BarMenu;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	/**
	 * <h4>MainFrame is the app's main frame.</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //
	/**
	 * Constantes de classe
	 */
	public final static Integer MAX_WIDTH = 1024;
	public final static Integer SIZE_X = (Toolkit.getDefaultToolkit()
			.getScreenSize().width > MAX_WIDTH) ? MAX_WIDTH : 4 * Toolkit
			.getDefaultToolkit().getScreenSize().width / 5;
	public final static Integer SIZE_Y = 4 * Toolkit.getDefaultToolkit()
			.getScreenSize().height / 5;
	public final static String TITLE = "UML Creator";

	// ----------------------------------------- //
	// ----------------ATTRIBUTES--------------- //
	// ----------------------------------------- //
	/**
	 * Application's menu
	 * 
	 * @see BarMenu
	 */
	private BarMenu menu;

	/**
	 * Main container
	 * 
	 * @see ConteneurGlobal
	 */
	private GlobalContainer globalContainer;

	String PATH_ICON_APPLICATION = Main._ICON_PATH + "icone_appli.png";

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //

	/**
	 * Constructor
	 */
	public MainFrame() {
		super();

		buildLookAndFeel();

		setTitle(TITLE);
		setSize(SIZE_X, SIZE_Y);
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		buildMenuTop();
		buildGlobalContainer();

                java.net.URL imgURL = getClass().getResource(PATH_ICON_APPLICATION);
		ImageIcon img = new ImageIcon(imgURL);
		setIconImage(img.getImage());

		setVisible(true);
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //
	/**
	 * use a look & feel
	 */
	private void buildLookAndFeel() {
		try {
			if (System.getProperty("os.name").toLowerCase().contains("linux"))
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			else if (System.getProperty("os.name").toLowerCase()
					.contains("windows"))
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			else
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	/**
	 * Make menu
	 */
	private void buildMenuTop() {
		menu = new BarMenu();
		this.setJMenuBar(menu);
		ListenerBarMenu e = new ListenerBarMenu(this);
	}

	/**
	 * Make global container
	 */
	private void buildGlobalContainer() {
		globalContainer = new GlobalContainer(this);
		this.getContentPane().add(globalContainer, BorderLayout.CENTER);
	}

	// ----------------------------------------- //
	// ---------------GETTERS------------------- //
	// ----------------------------------------- //

	public BarMenu getMenu() {
		return menu;
	}

	public GlobalContainer getGlobalContainer() {
		return globalContainer;
	}

	// ----------------------------------------- //
	// ----------------SETTERS------------------ //
	// ----------------------------------------- //
	public void setMenu(BarMenu menu) {
		this.menu = menu;
	}

	public void setGlobalContainer(GlobalContainer globalContainer) {
		this.globalContainer = globalContainer;
	}

}
