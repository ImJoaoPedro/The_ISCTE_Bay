package client;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {

	JFrame frame = new JFrame("THE ISCTE BAE");
	JPanel panel = new JPanel();
	
	public GUI() {
		frame.add(panel);
		frame.show();
	}
	
}
