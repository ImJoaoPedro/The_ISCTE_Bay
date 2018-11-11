package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ClientUI {

	// Top Panel - Text, Field, Search
	private final int TOP_PANEL_GRID_ROWS = 0;
	private final int TOP_PANEL_GRID_COLUMNS = 3;

	// Bottom right panel - Download and JProgressBar
	private final int RIGHT_PANEL_GRID_ROWS = 2;
	private final int RIGHT_PANEL_GRID_COLUMNS = 0;

	// Frame position
	private final int WINDOW_X = 250;
	private final int WINDOW_Y = 200;

	// Components
	private JFrame frame;
	private JTextField text;
	private JButton searchButton;
	private JButton downloadButton;

	// List
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList<String> list = new JList<>(model);

	private Client client;

	public ClientUI(Client client) {
		this.client = client;
		loadGUI();
	}

	private void loadGUI() {
		loadFrame();
		loadPanels();
		frame.pack();
	}

	private void loadPanels() {
		loadTopPanel();
		loadLeftPanel();
		loadRightPanel();
	}

	private void loadFrame() {
		frame = new JFrame("The ISCTE Bay");
		addWindowListener();
		frame.setLayout(new BorderLayout());
		frame.setLocation(WINDOW_X, WINDOW_Y);
	}

	// Closing Window PopUp
	private void addWindowListener() {
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (closingDialog() == 0) {
					client.sendExitSignal();
					System.exit(0);
				}
			}
		};
		frame.addWindowListener(exitListener);
	}

	private int closingDialog() {
		String closingQuestion = "Are you sure you want to Close Application?";
		String exit = "Exit Confirmation";
		return JOptionPane.showOptionDialog(null, closingQuestion, exit, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
	}

	// TopPanel - search
	private void loadTopPanel() {
		JPanel panel = new JPanel(new GridLayout(TOP_PANEL_GRID_ROWS, TOP_PANEL_GRID_COLUMNS));
		JLabel label = new JLabel("Texto a procurar:");
		text = new JTextField("");
		loadSearchButton();
		panel.add(label);
		panel.add(text);
		panel.add(searchButton);
		frame.add(panel, BorderLayout.NORTH);
	}

	private void loadSearchButton() {
		searchButton = new JButton("Procurar");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.clear();
				if (text.getText().equals("")) {
					client.sendConsultSignal();
				} else {
					client.sendSearchSignal();
				}
			}
		});
	}

	// LeftPanel - JList
	private void loadLeftPanel() {
		JPanel panel = new JPanel();
		panel.add(list);
		frame.add(panel, BorderLayout.WEST);
	}

	// RightPanel - Download Action and Progressbar
	private void loadRightPanel() {
		JPanel panel = new JPanel(new GridLayout(RIGHT_PANEL_GRID_ROWS, RIGHT_PANEL_GRID_COLUMNS));
		JProgressBar progressbar = new JProgressBar();
		// TODO progressbar load
		loadDownloadButton();
		panel.add(downloadButton);
		panel.add(progressbar);
		frame.add(panel, BorderLayout.EAST);
	}

	private void loadDownloadButton() {
		downloadButton = new JButton("Descarregar");
		downloadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Actiontoadd
			}
		});
	}

	public void start() {
		frame.setVisible(true);
	}

	DefaultListModel<String> getListModel() {
		return model;
	}

	String getText() {
		return text.getText();
	}

}
