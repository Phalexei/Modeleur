package com.github.tomap.modeler.controler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.github.tomap.modeler.view.GlobalContainer;
import com.github.tomap.modeler.view.PanelEditor;
import com.github.tomap.modeler.view.SelectorClass;
import com.github.tomap.modeler.view.SelectorObject;
import com.github.tomap.modeler.view.dialog.DialogClass;
import com.github.tomap.modeler.view.dialog.DialogInterface;
import com.github.tomap.modeler.view.dialog.DialogBinaryRelation;
import com.github.tomap.modeler.view.dialog.DialogNrelation;

public class ListenerEditor implements MouseListener {
	/**
	 * <h4>ListenerTabbedPane listens the tabbed pane</h4>
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS --------------- //
	// ----------------------------------------- //

	private final static int CLASS_SELECTOR = 0;
	private final static int OBJECT_SELECTOR = 1;

	// ----------------------------------------- //
	// ----------------ATRIBUTES---------------- //
	// ----------------------------------------- //

	private GlobalContainer cGlobal;
	private SelectorClass panelClass;
	private SelectorObject panelObject;
	@SuppressWarnings("unused")
	private PanelEditor panelEditor;

	private DialogClass dialogClass;
	private DialogInterface dialogInterface;
	

	// ----------------------------------------- //
	// --------------CONSTRUCTOR---------------- //
	// ------------------------------------------//

	public ListenerEditor(GlobalContainer cGlobal) {
		super();
		this.cGlobal = cGlobal;
		this.panelClass = cGlobal.getEditorContainer().getPanelClass();
		this.panelObject = cGlobal.getEditorContainer().getPanelObject();
		this.panelEditor = cGlobal.getEditorContainer().getPanelEditor();

		for (JPanel panel : panelClass.getPanels()) {
			panel.addMouseListener(this);
		}
		
		for (JPanel panel : panelObject.getPanels()) {
			panel.addMouseListener(this);
		}

		dialogClass = new DialogClass(cGlobal);
		dialogInterface = new DialogInterface(cGlobal);
	}

	// ----------------------------------------- //
	// -----------------METHODS----------------- //
	// ----------------------------------------- //

	private void getActionInClassEditor(int x, int y) {
		// panel package
		if ((x == panelClass.getPanels().get(0).getX())
				&& (y == panelClass.getPanels().get(0).getY())) {

			dialogClass.setVisible(true);

		}
		// panel class
		else if ((x == panelClass.getPanels().get(1).getX())
				&& (y == panelClass.getPanels().get(1).getY())) {

			dialogInterface.setVisible(true);

		} else if ((x == panelClass.getPanels().get(2).getX())
				&& (y == panelClass.getPanels().get(2).getY())) {

			if (cGlobal.getContainerTabbedPane().getPanelClass().getListClasses().size() > 0
					|| cGlobal.getContainerTabbedPane().getPanelClass().getListInterfaces().size() > 0) {
				DialogBinaryRelation dialogRelation = new DialogBinaryRelation(cGlobal);
				dialogRelation.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(cGlobal.getParent(),
						"There is no available class/interface");
			}

		} else if ((x == panelClass.getPanels().get(3).getX())
				&& (y == panelClass.getPanels().get(3).getY())) {

			if (cGlobal.getContainerTabbedPane().getPanelClass().getListClasses().size() > 0
					|| cGlobal.getContainerTabbedPane().getPanelClass().getListInterfaces().size() > 0) {
				DialogNrelation dialogNelation = new DialogNrelation(cGlobal);
				dialogNelation.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(cGlobal.getParent(),
						"There is no available class/interface");
			}
		}
	}

	private void getActionInObjectEditor(int x, int y) {
		if ((x == panelObject.getPanels().get(0).getX())
				&& (y == panelObject.getPanels().get(0).getY())) {

			JOptionPane.showMessageDialog(cGlobal.getParent(),
					"Feature not available");

		} else if ((x == panelObject.getPanels().get(1).getX())
				&& (y == panelObject.getPanels().get(1).getY())) {

			JOptionPane.showMessageDialog(cGlobal.getParent(),
					"Feature not available");

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		JPanel panel = (JPanel) e.getSource();
		int index = cGlobal.getContainerTabbedPane().getSelectedIndex();

		if (index == CLASS_SELECTOR) {
			getActionInClassEditor(panel.getX(), panel.getY());
		} else if (index == OBJECT_SELECTOR) {
			getActionInObjectEditor(panel.getX(), panel.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}