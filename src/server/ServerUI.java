package server;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class ServerUI {

	@SuppressWarnings("unused")
	private Server server;
	private JFrame frame;
	private JTextArea text;

	public ServerUI(Server server) {
		this.server = server;
		buildUI();
	}

	private void buildUI() {
		frame = new JFrame("Server");
		text = new JTextArea("Hi there!");
		frame.add(text);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(200, 400);
	}

	public void start() {
		frame.setVisible(true);
	}

	public void addToText(String line) {
		if (text.getText() != null) {
			text.setText(text.getText() + "\n" + line);
		} else
			text.setText(line);
	}

}
