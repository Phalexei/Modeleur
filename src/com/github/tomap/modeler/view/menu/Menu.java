package com.github.tomap.modeler.view.menu;

import com.github.tomap.modeler.main.Main;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class Menu extends JMenu {
	/**
	 * Menu allows to build menu's items
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// -------------- INFORMATIONS ------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// ----------------ATTRIBUTES--------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //

	Menu() {
		super();
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	/**
	 * Allows to build a JMenuItem (without keystroke)
	 * 
	 * @param item
	 *            : JMenuItem to build.
	 * @param titre
	 *            : JmenuItem's title
	 * @return : JMenuItem
	 */
	public JMenuItem buildMenuItem(JMenuItem item, String titre, String iconPath) {
		item = new JMenuItem(titre);
		item.setIcon(new ImageIcon(getClass()
				.getResource(Main._ICON_PATH + iconPath)));
		item.setName(titre);

		return item;
	}

	/**
	 * Allows to build a JMenuItem (with keystroke)
	 * 
	 * @param item
	 *            :  JMenuItem to build.
	 * @param titre
	 *            :  JmenuItem's title
	 * @param ks
	 *            : JMenuItem's keystroke
	 * @return : JMenuItem
	 */
	public JMenuItem buildMenuItem(JMenuItem item, String titre, KeyStroke ks,
			String iconPath) {
		item = buildMenuItem(item, titre, iconPath);
		item.setAccelerator(ks);

		return item;
	}
}
