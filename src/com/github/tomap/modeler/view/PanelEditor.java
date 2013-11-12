package com.github.tomap.modeler.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelEditor extends JPanel{
	
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //
	
	public static Integer			SIZE_X	= 200;
	public static Integer			SIZE_Y	= GlobalContainer.SIZE_Y;

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //
	
	
	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	
	public PanelEditor(){
		super();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //
	
}
