package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		loadSearchButton();

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

		loadDownloadButton();

		panel.add(downloadbutton);

		JProgressBar progressbar = new JProgressBar();
		// Progressbar too thin

		panel.add(progressbar);

		frame.add(panel, BorderLayout.EAST);
	}

	private void loadSearchButton() {

		searchbutton = new JButton("Procurar");

		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Procurar");
				// textfield.getText()
				// listar users/CLT ??
			}
		});

	}

	private void loadDownloadButton() {

		downloadbutton = new JButton("Descarregar");

		downloadbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Descarregar");
			}
		});

	}

	public JButton getSearchButton() {
		return searchbutton;
	}

	public JButton getDownloadButton() {
		return downloadbutton;
	}

	private void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GUI window = new GUI();
		window.open();
	}

}
