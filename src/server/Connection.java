package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import models.User;

public class Connection extends Thread {

	/*
	 * Criar boolean para prevenir users nao inscritos pedirem o CLT
	 */

	private final String JOIN_PREFIX = "INSC";
	private final String CONSULT_PREFIX = "CLT";

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<User> users;
	private User user;

	public Connection(Socket socket, ArrayList<User> users) {
		super();
		this.socket = socket;
		this.users = users;
	}

	public void run() {
		try {
			connect();
			serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void connect() throws IOException {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}

	private void serve() throws IOException {
		while (!socket.isClosed()) {
			readMessage();
		}
		users.remove(user);
	}

	private void readMessage() throws IOException {
		String temp = in.readLine();
		if (temp == null) {
			// throw io exception
		} else if (temp.startsWith(JOIN_PREFIX)) {
			String tmp[] = temp.split(" ");
			String name = tmp[1];
			String adress = tmp[2];
			int port = Integer.parseInt(tmp[3]);
			user = new User(name, adress, port, socket);
			users.add(user);
			out.println("Successfully Joined!");
			System.out.println("A new user has connected!");
		} else if (temp.startsWith(CONSULT_PREFIX)) {
			for (User user : users) {
				out.println("CLT " + user.getAddress() + " " + user.getPort());
			}
		}
	}

}
