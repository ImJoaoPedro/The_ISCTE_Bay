package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import server.Server;

public class Client {

	private final String serverAddress = "127.0.0.1";
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private GUI gui;

	public Client() {
		launchClient();
	}

	private void launchClient() {
		makeGui();
		joinServer();
	}

	private void makeGui() {
		gui = new GUI(this);
		gui.start();
	}

	private void joinServer() {
		setConnections();
		registerAtServer();
		startConnection();
	}

	private void startConnection() {
		try {
			while (true) {
				String msg = in.readLine();
				if (msg != null) {
					System.out.println(msg);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void sendExitSignal() {
		out.println("EXIT");
	}

	void sendConsultSignal() {
		out.println("CLT");
	}

	private void registerAtServer() {
		out.println("INSC " + serverAddress + " " + socket.getLocalPort());
	}

	private void setConnections() {
		try {
			socket = new Socket(serverAddress, Server.socketPort);
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
