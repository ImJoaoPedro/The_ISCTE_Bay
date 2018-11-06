package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GUI {

//	private static final int INITIAL_WIDTH = 10;
//	private static final int INITIAL_HEIGHT = 20;
	private JFrame frame;

	public GUI() {
		frame = new JFrame("The ISCTE Bay");

		frame.setLayout(new FlowLayout()); //nao sei qual o melhor layout TODO
		
		loadUI();
		
		frame.pack();

	}

	
	private void loadUI() {
		
		//TODO Right layout, Actions, List needs Objects
		
		// jlabel, textfield, jbutton, jlist, jbutton e jprogressbar

		JLabel text = new JLabel ("Texto a procurar:");
		frame.add(text);
		
		JTextField textfield = new JTextField();
		frame.add(textfield);
		
		// frame.add(new JList()); precisa de uma lista de objectos especificos que n sei quais sao 
		
		
		frame.add(new JProgressBar());

		//TODO doesn't have any action
		JButton searchbutton = new JButton("Procurar");
		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Procurar");
				//textfield.getText()
				//listar users/CLT ??
			}
		});
		
		//TODO doesn't have any action
		JButton downloadbutton = new JButton("Descarregar");
		downloadbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Descarregar");
			}
		});
		
		frame.add(searchbutton);

		frame.add(downloadbutton);
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	private void open() {
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		GUI window = new GUI();
		window.open();
	}

}
