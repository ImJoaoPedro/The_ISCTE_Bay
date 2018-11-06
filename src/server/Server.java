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

	private int socketPort;
	private ServerSocket server;
	private Socket socket;
	private ArrayList<User> users;

	public Server(String[] args) {
		checkArgs(args);
		users = new ArrayList<User>();
		System.out.println("Starting Server at port " + socketPort);
		startServer();
	}

	private void checkArgs(String[] args) {
		// check if args are not null, else defaults to 8080
		if (args != null) {
			socketPort = Integer.parseInt(args[0]);
		} else
			socketPort = 8080;
	}

	private void startServer() {
		try {
			// Starts accepting connections on the server
			server = new ServerSocket(socketPort);

			// Test
			while (true) {
				socket = server.accept();

				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();

				if (msg.startsWith("INSC")) {
					String temp[] = msg.split(" ");
					users.add(new User(temp[1], temp[2], Integer.parseInt(temp[3])));
				} else if(msg.startsWith("CLT")) {
					//TO-DO
				}

				System.out.println(users.size());

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
