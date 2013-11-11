package com.github.tomap.modeler.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.github.tomap.modeler.view.FrameAbout;
import com.github.tomap.modeler.view.MainFrame;

public class ListenerFrameAbout implements ActionListener {
	/**
	 * <h4>ListenerFrameAbout allows to listen AboutFrame class</h4>
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- ATTRIBUTES--------------- //
	// ----------------------------------------- //

	/**
	 * Main Frame
	 * 
	 * @see MainFrame
	 */
	private MainFrame mainFrame;
	/**
	 * About frame
	 * 
	 * @see FrameAbout
	 */
	private FrameAbout Aboutframe;

	// ----------------------------------------- //
	// ------------- CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	/**
	 * Constructor
	 * 
	 * @param fp
	 *            the main frame
	 * @param fap
	 *            the information frame
	 */
	public ListenerFrameAbout(MainFrame fp, FrameAbout fap) {
		Aboutframe = fap;
		mainFrame = fp;

		Aboutframe.getExit().addActionListener(this);
	}

	// ----------------------------------------- //
	// ---------------- LISTENERS -------------- //
	// ----------------------------------------- //

	@Override
	/**
	 * catch information frame
	 * @param e event
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Aboutframe.getExit()) {
			Aboutframe.dispose();
			mainFrame.setEnabled(true);
		}
	}

	// ----------------------------------------- //
	// -------------- GETTERS--- --------------- //
	// ----------------------------------------- //
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	// ----------------------------------------- //
	// --------------- SETTERS-- --------------- //
	// ----------------------------------------- //

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
