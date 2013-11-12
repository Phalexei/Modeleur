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
public class SelectorClass extends JPanel{
	
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
	
	public SelectorClass(){
		super();
		this.setLayout(new GridLayout(2,2,2,2));
		this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		
		panels = new LinkedList<JPanel>();
		
		JPanel a_class = createSquareJPanel(new Color(218,224,241), 30,"Class",Main._ICON_PATH+"class_image.png");
        JPanel an_interface = createSquareJPanel(new Color(218,224,241), 30,"Interface",Main._ICON_PATH+"interface_image.png");
        JPanel aBinaryRelation = createSquareJPanel(new Color(218,224,241), 30," Binary Relation",Main._ICON_PATH+"relation_image.png");
        JPanel aNaryRelation = createSquareJPanel(new Color(218,224,241), 30," N Relation",Main._ICON_PATH+"relation_image.png");
        
        panels.add(a_class);
        panels.add(an_interface);
        panels.add(aBinaryRelation);
        panels.add(aNaryRelation);
        
       this.add(a_class);
       this.add(an_interface);
       this.add(aBinaryRelation);
       this.add(aNaryRelation);

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

	
	// ----------------------------------------- //
	// ----------GETTERS/SETTERS---------------- //
	// ----------------------------------------- //
	
	public List<JPanel> getPanels() {
		return panels;
	}

	public void setPanels(List<JPanel> panels) {
		this.panels = panels;
	}

	

}
