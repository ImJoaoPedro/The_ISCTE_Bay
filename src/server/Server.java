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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import models.User;

public class Server {

	public static final int DEFAULT_PORT = 8080;
	public static final String JOIN_PREFIX = "INSC";
	public static final String USER_PREFIX = "CLT";

	private int socketPort;
	private ServerSocket serversocket;
	private Socket socket;
	//private ArrayList<User> users = new ArrayList<>();

	public Server() {

		socketPort = DEFAULT_PORT;
		//checkArgs(args);
		System.out.println("Starting Server at port " + socketPort);
	}

	private void checkArgs(String[] args) {
		// check if args are not null, else defaults to 8080
		if (args != null) {
			socketPort = Integer.parseInt(args[0]);
		} else
			socketPort = DEFAULT_PORT;
	}

	public void startServer()  {

		try {
			// Starts accepting connections on the server
			serversocket = new ServerSocket(socketPort);
			System.out.println("");
			// Test
			while (true) {
				socket = serversocket.accept();

				//BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//				String msg = in.readLine();
//				readMessage(msg);

				new Connection(socket).start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serversocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//	private void readMessage(String message) {
//
//		if (message.startsWith(JOIN_PREFIX)) {
//
//			String user[] = message.split(" ");
//			String name = user[1];
//			String adress = user[2];
//			int port = Integer.parseInt(user[3]);
//
//			users.add(new User(name, adress, port));
//		} else if (message.startsWith(USER_PREFIX)) {
//			// TODO
//		}
//
//	}

	// restos caso necessário

//	if (msg.startsWith("INSC")) {
//		String temp[] = msg.split(" ");
//		users.add(new User(temp[1], temp[2], Integer.parseInt(temp[3])));
//	} else if (msg.startsWith("CLT")) {
//		//TODO
//	}

}
