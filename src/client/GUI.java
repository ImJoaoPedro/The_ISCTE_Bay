package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GUI {

	// Top Panel - Text, Field, Search
	private final int TOP_PANEL_GRID_ROWS = 0;
	private final int TOP_PANEL_GRID_COLUMNS = 3;

	// Bottom right panel - Download and JProgressBar
	private final int RIGHT_PANEL_GRID_ROWS = 2;
	private final int RIGHT_PANEL_GRID_COLUMNS = 0;

	private JFrame frame;
	private JTextField textfield;
	// jlist
	private JButton searchbutton;
	private JButton downloadbutton;
	private Client client;

	public GUI(Client client) {
		this.client = client;

		loadGUI();
	}

	// TODO JList<Object?>, ProgressBar thin, Search Action

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

		loadFrameClosing();

		frame.setLayout(new BorderLayout());

	}

	// Closing Window PopUp
	private void loadFrameClosing() {

		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sendExitSignal();
			}
		};

		frame.addWindowListener(exitListener);

	}

	private int closingDialog() {

		String closingquestion = "Are you sure you want to Close Application?";
		String exit = "Exit Confirmation";

		return JOptionPane.showOptionDialog(null, closingquestion, exit, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
	}

	private void sendExitSignal() {

		if (closingDialog() == 0) {
			client.sendExitSignal();
			System.exit(0);
		}

	}

	//TopPanel - search
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

	private void loadSearchButton() {

		searchbutton = new JButton("Procurar");

		searchbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendConsultSignal();
			}
		});
	}

	//LeftPanel - JList
	private void loadLeftPanel() {
		JPanel panel = new JPanel();

		// add JList

		frame.add(panel, BorderLayout.WEST);
	}

	//RightPanel - Download Action and Progressbar
	private void loadRightPanel() {
		JPanel panel = new JPanel(new GridLayout(RIGHT_PANEL_GRID_ROWS, RIGHT_PANEL_GRID_COLUMNS));
		JProgressBar progressbar = new JProgressBar();
		// TODO progressbar probably will need to be a field and will need a load for
		// itself

		loadDownloadButton();

		panel.add(downloadbutton);
		panel.add(progressbar);

		frame.add(panel, BorderLayout.EAST);
	}

	private void loadDownloadButton() {

		downloadbutton = new JButton("Descarregar");

		downloadbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Actiontoadd
			}
		});
	}

	// TODO Textfield may need action

	public void start() {
		frame.setVisible(true);
	}

}
