package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import server.Server;

public class Client {

	// TODO thread?

	public static final String BASE_IP = "127.0.0.1";

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int num;

	public Client(int num) {
		this.num = num;
	}

	public void launchClient() {
		makeGui();
		joinServer();
	}

	private void makeGui() {
		GUI gui = new GUI();
		gui.getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("CLT");
			}
		});
	}

	private void joinServer() {

		// GET CURRENT NUMBER FROM SERVER BEFORE "INSC"
		int currentnumber = num + 1;
		System.out.println("I'm the client number " + (currentnumber));

		try {
			// create socket
			socket = new Socket(BASE_IP, Server.DEFAULT_PORT);

			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out.println("INSC " + (currentnumber) + " " + BASE_IP + " " + socket.getLocalPort());
			out.println("CLT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				String msg = in.readLine();
				if (msg != null) {
					System.out.println(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
