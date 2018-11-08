package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GUI {
 
	// Top Panel - Text, Field, Search
	private static final int TOP_PANEL_GRID_ROWS = 0;
	private static final int TOP_PANEL_GRID_COLUMNS = 3;

	// Bottom right panel - Download and JProgressBar
	private static final int RIGHT_PANEL_GRID_ROWS = 2;
	private static final int RIGHT_PANEL_GRID_COLUMNS = 0;

	private JFrame frame;
	private JTextField textfield;
	// jlist
	private JButton searchbutton;
	private JButton downloadbutton;

	public GUI() {

		frame = new JFrame("The ISCTE Bay");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		loadGUI();

		frame.pack();
		frame.setVisible(true);
	}
	
	//TODO JList<Object?>, ProgressBar thin, Search Action

	private void loadGUI() {
		loadTopPanel();
		loadLeftPanel();
		loadRightPanel();
	}

	private void loadTopPanel() {

		JPanel panel = new JPanel(new GridLayout(TOP_PANEL_GRID_ROWS, TOP_PANEL_GRID_COLUMNS));
		JLabel label = new JLabel("Texto a procurar:");
		textfield = new JTextField();
		searchbutton = new JButton("Procurar");
		
		panel.add(label);
		panel.add(textfield);
		panel.add(searchbutton);
		
		frame.add(panel, BorderLayout.NORTH);

	}

	private void loadLeftPanel() {
		JPanel panel = new JPanel();

		// add JList

		frame.add(panel, BorderLayout.WEST);
	}

	private void loadRightPanel() {
		JPanel panel = new JPanel(new GridLayout(RIGHT_PANEL_GRID_ROWS, RIGHT_PANEL_GRID_COLUMNS));
		JProgressBar progressbar = new JProgressBar();
		downloadbutton = new JButton("Descarregar");	
		
		panel.add(downloadbutton);
		panel.add(progressbar);

		frame.add(panel, BorderLayout.EAST);
	}

	public JButton getSearchButton() {
		return searchbutton;
	}

	public JButton getDownloadButton() {
		return downloadbutton;
	}
	
	//get textfield

}
