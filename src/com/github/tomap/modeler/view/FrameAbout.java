package com.github.tomap.modeler.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import com.github.tomap.modeler.controler.ListenerFrameAbout;
import com.github.tomap.modeler.main.Main;

@SuppressWarnings("serial")
public class FrameAbout extends JWindow {
	/**
	 * FrameAbout is the information frame
	 * 
	 * 
	 * @author Alexis CHRETIENNE
	 */
	// ----------------------------------------- //
	// --------------- CONSTANTS- -------------- //
	// ----------------------------------------- //

	private final static Color COULEUR_BORDURE = Color.BLACK;

	private final static String VERSION = "1.0";
	private final static String REVISON = "023cd5c857";
	private final static Dimension dimension = new Dimension(420, 320);

	// ----------------------------------------- //
	// --------------- ATTRIBUTES--------------- //
	// ----------------------------------------- //

	private MainFrame mainFrame;

	private JButton exit;
	private JEditorPane websiteLink;

	private ListenerFrameAbout listener;

	// ----------------------------------------- //
	// ------------- CONSTRUCTOR-- ------------- //
	// ----------------------------------------- //

	/**
	 * Constructor
	 * 
	 * @param fp
	 *            the main frame
	 * 
	 */
	public FrameAbout(MainFrame fp) {
		setMainFrame(fp);

		this.setSize(dimension);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		initComponenent();
		listener = new ListenerFrameAbout(mainFrame, this);
	}

	// ----------------------------------------- //
	// ---------------- METHODS ---------------- //
	// ----------------------------------------- //
	/**
	 * initialize the frame
	 */
	private void initComponenent() {
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(BorderFactory.createLineBorder(COULEUR_BORDURE));

		container.add(top(), BorderLayout.CENTER);
		container.add(bottom(), BorderLayout.PAGE_END);

		getContentPane().add(container);
	}

	/**
	 * make information panel
	 * 
	 * @return information panel
	 */
	private JPanel top() {
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource(
				Main._ICON_PATH + "logo_150.png")));
		logo.setVerticalAlignment(JLabel.NORTH);
		logo.setHorizontalAlignment(JLabel.CENTER);
		logo.setBackground(Color.WHITE);

		JEditorPane auteurs = new JEditorPane("text/html", "<html>" + "<h3>"
				+ "Authors :"
				+ "<ul>" 
					+"<li>Tom ANDRE</li>"
					+"<li>Gaetan FARAMAZ</li>"
					+"<li>Teophile MONTOVERT</li>"
					+"<li>Alexis CHRETIENNE</li>"
				+ "</ul>" 
				+ "</html>");
		
		websiteLink = new JEditorPane(
				"text/html",
				"<html>"
						+ "<h3> Available on Github : "
						+ "https://github.com/tom-ap/Modeleur/"
						+ "</h3>" + "</html>");
		websiteLink.setEditable(false);


		JPanel top_left = new JPanel();
		top_left.add(logo);
		top_left.setBackground(Color.WHITE);

		JPanel top_right = new JPanel(new BorderLayout());
		top_right.add(auteurs, BorderLayout.PAGE_START);

		JPanel top_top = new JPanel(new GridLayout(1, 3));

		top_top.add(top_left);
		top_top.add(top_right);
		top_top.setBackground(Color.WHITE);

		JPanel top = new JPanel(new BorderLayout());
		top.setBackground(Color.WHITE);

		JEditorPane infos = new JEditorPane("text/html", "<html>"
				+ "<p style=\"text-align : justify\">"
					+ "UML Creator allows to make UML diagramm. "
					+ "It just manages (and checks) class and objects diagramms."
				+ "</p>" 
					+ "</html>"

		);

		top.add(top_top, BorderLayout.NORTH);
		top.add(infos, BorderLayout.CENTER);
		top.add(websiteLink, BorderLayout.SOUTH);

		return top;
	}

	/**
	 * make information panel (bottom)
	 * 
	 * @return information panel
	 */
	private JPanel bottom() {
		JLabel revision = new JLabel("Release " + REVISON);
		revision.setForeground(Color.GRAY);
		JLabel version = new JLabel("UML Creator " + VERSION);
		version.setForeground(Color.GRAY);
		exit = new JButton("Close");

		JPanel bottom_left = new JPanel(new FlowLayout());
		bottom_left.setBackground(Color.WHITE);
		bottom_left.add(version);

		JPanel bottom_center = new JPanel();
		bottom_center.setBackground(Color.WHITE);
		bottom_center.add(revision);

		JPanel bottom_right = new JPanel(new FlowLayout());
		bottom_right.setBackground(Color.WHITE);
		bottom_right.add(exit);

		JPanel bottom = new JPanel(new GridLayout(1, 3));
		bottom.setBackground(Color.WHITE);

		bottom.add(bottom_left);
		bottom.add(bottom_center);
		bottom.add(bottom_right);

		return bottom;
	}

	// ----------------------------------------- //
	// ----------- GETTERS / SETTERS------------ //
	// ----------------------------------------- //
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	public JEditorPane getWebsiteLink() {
		return websiteLink;
	}

	public void setWebsiteLink(JEditorPane websiteLink) {
		this.websiteLink = websiteLink;
	}

	public ListenerFrameAbout getListener() {
		return listener;
	}

	public void setListener(ListenerFrameAbout listener) {
		this.listener = listener;
	}

}
