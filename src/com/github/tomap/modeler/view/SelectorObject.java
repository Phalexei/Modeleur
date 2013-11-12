package com.github.tomap.modeler.view;

import com.github.tomap.modeler.main.Main;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelectorObject extends JPanel{
	
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //
	
	public static Integer			SIZE_X	= 200;
	public static Integer			SIZE_Y	= GlobalContainer.SIZE_Y;

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //
	private List<JPanel> 				panels;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ----------------------------------------- //
	
	public SelectorObject(){
		super();
		this.setLayout(new GridLayout(2,2,2,2));
		this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		
		panels = new LinkedList<JPanel>();
		
		JPanel an_object = createSquareJPanel(new Color(218,224,241), 30,"Object",Main._ICON_PATH + "class_image.png");
        JPanel a_relation = createSquareJPanel(new Color(218,224,241), 30,"Relation",Main._ICON_PATH + "relation_image.png");
        
        panels.add(an_object);
        panels.add(a_relation);
        
       this.add(an_object);
       this.add(a_relation);
	}
	
	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //
	
	private JPanel createSquareJPanel(Color color, int size, String title, String imagePath)
    {
        final JPanel tempPanel = new JPanel();
        tempPanel.setBackground(color);
        tempPanel.setMinimumSize(new Dimension(size, size));
        tempPanel.setMaximumSize(new Dimension(size, size));
        tempPanel.setPreferredSize(new Dimension(size, size));
        
        java.net.URL imgURL = getClass().getResource(imagePath);
        ImageIcon img = new ImageIcon(imgURL);
        JLabel l = new JLabel(img, JLabel.CENTER);
        tempPanel.add(l);
        JLabel l2 = new JLabel(title);
        tempPanel.add(l2);
        
        tempPanel.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent arg0) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            	
            	Point p = new Point(e.getX(), e.getY());
            	if (tempPanel.contains(p)){
            		tempPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            	}
            }

        });
        
        return tempPanel;
    }

	public List<JPanel> getPanels() {
		return panels;
	}

	public void setPanels(List<JPanel> panels) {
		this.panels = panels;
	}
	
	

}
