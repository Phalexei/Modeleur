package com.github.tomap.modeler.main;

import com.github.tomap.modeler.model.diagramClass.exception.BadTypeException;
import com.github.tomap.modeler.view.MainFrame;

public class Main {

	/**
	 * <h4>Main is the main class (allows to launch program)</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */

	/**
	 * Main method
	 * 
	 * @param args
	 *            : array of parameters
	 * @throws BadTypeException
	 * 
	 */

	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //
	public static String _ICON_PATH = "/com/github/tomap/modeler/images/icones/";

	// ----------------------------------------- //
	// --------------- MAIN APP----------------- //
	// ----------------------------------------- //

	public static void main(String[] args) throws BadTypeException {

		MainFrame f = new MainFrame();
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

}
