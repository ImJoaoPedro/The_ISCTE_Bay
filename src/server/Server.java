/*
TO-DO
 
 - Server NEEDS to be a Thread
 	it will have a flag in a while cycle, and whenever its interrupted, accepts inbound connections
 	maybe for testing, do a while(true)??
 	
  - Implement close socket and server in separate function
  
  - Interrupts from clients NEED to send a string.
  	the string will then be parsed (in its separate function) and will execute the proper command(INSC, CLT, EXIT, etc)
 
  - Set socket, server, and such as parameters of the class instead of temporary objects inside the functions for ease of access
  
 
*/

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import models.User;

public class Server {

	public static int socketPort = 8080;
	private ServerSocket serversocket;
	private Socket socket;
	private ArrayList<User> users = new ArrayList<>();
	private ServerUI gui;

	public Server(String[] args) {
		checkArgs(args);
		startUI();
		startServer();
	}

	private void startServer() {
		try {
			// Starts accepting connections on the server
			serversocket = new ServerSocket(socketPort);
			while (true) {
				socket = serversocket.accept();
				new Connection(socket, users, gui).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkArgs(String[] args) {
		// check if args are not null, else defaults to 8080
		if (args != null)
			socketPort = Integer.parseInt(args[0]);
	}

	private void startUI() {
		gui = new ServerUI(this);
		gui.start();
		gui.addToText("Starting Server at port " + socketPort);
	}

}
