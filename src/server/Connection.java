package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import models.User;

public class Connection extends Thread {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<User> users;
	private User user;
	private boolean hasLeft;
	private ServerUI gui;

	public Connection(Socket socket, ArrayList<User> users, ServerUI gui) {
		super();
		this.socket = socket;
		this.users = users;
		this.gui = gui;
	}

	public void run() {
		try {
			connect();
			serve();
		} catch (IOException e) {
			if (!hasLeft) {
				exitUser();
			}
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
	}

	private void readMessage() throws IOException {
		String input = in.readLine();
		if (input == null) {
			throw new IOException();
		} else if (input.startsWith("INSC")) {
			addUser(input);
		} else if (input.startsWith("CLT")) {
			consultUsers();
		} else if (input.startsWith("EXIT")) {
			exitUser();
		} else if (input.startsWith("SRC")) {
			searchForFile(input);
		}
	}

	private synchronized void searchForFile(String s) {
		String[] tmp = s.split(" ");
		String word = tmp[1];
		for (User user : users) {
			for (File file : user.getFiles()) {
				if (file.getName().contains(word)) {
					out.println("FOUND " + file.getName() + ", " + file.length() + " bytes");
				}
			}
		}
	}

	private void addUser(String s) {
		String[] tmp = s.split(" ");
		user = new User(tmp[1], Integer.parseInt(tmp[2]), socket);
		synchronized (users) {
			users.add(user);
			out.println("MSG Successfully Joined!");
			gui.addToText("A new user has connected!");
		}
	}

	private synchronized void consultUsers() {
		for (User user : users) {
			out.println("CLT " + user.getAddress() + " " + user.getPort());
		}
		out.println("END");
	}

	private synchronized void exitUser() {
		closeSocket();
		users.remove(user);
		gui.addToText("A user has disconnected!");
		hasLeft = true;
	}

	private void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			gui.addToText("Error closing socket!");
		}
	}

}
