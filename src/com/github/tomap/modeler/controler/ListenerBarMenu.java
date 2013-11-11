package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.github.tomap.modeler.view.FrameAbout;
import com.github.tomap.modeler.view.MainFrame;
import com.github.tomap.modeler.view.menu.BarMenu;
import com.github.tomap.modeler.view.menu.MenuAbout;
import com.github.tomap.modeler.view.menu.FileMenu;

public class ListenerBarMenu implements ActionListener {
	/**
	 * <h4>ListenerBarMenu allows to listen the BarMenu class</h4>
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */

	// ----------------------------------------- //
	// --------------- ATTRIBUTES -------------- //
	// ----------------------------------------- //

	/**
	 * Main frame
	 * 
	 * @see MainFrame
	 */
	private MainFrame mainFrame;
	/**
	 * Menu bar
	 * 
	 * @see BarMenu
	 */
	private BarMenu bm;
	/**
	 * Menu File
	 * 
	 * @see FileMenu
	 */
	private FileMenu mf;
	/**
	 * Menu About
	 * 
	 * @see MenuAbout
	 */
	private MenuAbout ma;
	/**
	 * information frame
	 * 
	 * @see FrameAbout
	 */
	private FrameAbout frameAbout;

	// ----------------------------------------- //
	// ------------- CONSTRUCTOR---------------- //
	// ----------------------------------------- //

	/**
	 * Constructor
	 * 
	 * @param the
	 *            main frame
	 */
	public ListenerBarMenu(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.bm = mainFrame.getMenu();

		initListenersMenuFile();
		initListenersMenuAbout();
	}

	// ----------------------------------------- //
	// ---------------- METHODS ---------------- //
	// ----------------------------------------- //

	/**
	 * Add listener to menu's tab
	 */
	private void initListenersMenuFile() {
		mf = (FileMenu) bm.getFileMenu();
		/*
		mf.getNewFile().addActionListener(this);
		mf.getSaveFile().addActionListener(this);
		*/
		mf.getExit().addActionListener(this);

	}

	/**
	 * Add listener to menu's items
	 */
	private void initListenersMenuAbout() {
		ma = (MenuAbout) bm.getMenuABout();
		//ma.getHelp().addActionListener(this);
		ma.getAbout().addActionListener(this);
	}

	/**
	 * launch dialog with ERROR
	 * 
	 * @param message
	 *            to display
	 */
	@SuppressWarnings("unused")
	private void lancerMessageErreur(String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	/**
	 * Catch an item's event
	 * @param e event (clic)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mf.getNewFile()) {
			// TODO
		}
		else if (e.getSource() == mf.getSaveFile()) {
			// TODO
		} else if (e.getSource() == mf.getExit()) {
			System.exit(0);
		} else if (e.getSource() == ma.getAbout()) {
			setFrameAbout(new FrameAbout(mainFrame));
			mainFrame.setEnabled(false);
		}
	}
	
	// ----------------------------------------- //
	// ----------- GETTERS / SETTERS------------ //
	// ----------------------------------------- //

	public FrameAbout getFrameAbout() {
		return frameAbout;
	}

	public void setFrameAbout(FrameAbout frameAbout) {
		this.frameAbout = frameAbout;
	}

}
