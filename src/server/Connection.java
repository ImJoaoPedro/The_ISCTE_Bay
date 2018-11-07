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
	
	public static final int DEFAULT_PORT = 8080;
	public static final String JOIN_PREFIX = "INSC";
	public static final String USER_PREFIX = "CLT";
	private ArrayList<User> users = new ArrayList<>();

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Connection(Socket socket) {
		super();
		this.socket = socket;
	}
	
	public void run() {
		try {
			connect(socket);
			serve();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	private void connect(Socket socket) throws IOException {
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream())), true);
		
	}
	
	private void serve() throws IOException {
		while(true) {
			readMessage(in.readLine());
			sendMessage();
		}
	}
	
	
	private void readMessage(String message) {

		if (message.startsWith(JOIN_PREFIX)) {

			String user[] = message.split(" ");
			String name = user[1];
			String adress = user[2];
			int port = Integer.parseInt(user[3]);

			users.add(new User(name, adress, port));
		} 

	}
	
	private void sendMessage() {
		out.println(users);
		
		for (User u : users) {
			System.out.println("CLT" + u);
		}
		
	}
	

}
