/*
TO-DO
 
 - Server NEEDS to be a Thread
 	it will have a flag in a while cycle, and whenever its interrupted, accepts inbound connections
 	maybe for testing, do a while(true)??
 	
  - Implement close socket and server in separate function
  
  - Interrupts from clients NEED to send a string.
  	the string will then be parsed (in its separate funcion) and will execute the proper command(INSC, CLT, EXIT, etc)
 
  - Set socket, server, and such as parameters of the class instead of temporary objects inside the functions for ease of access
  
 
*/

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private int socketPort;

	public Server(String[] args) {
		checkArgs(args);
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
			ServerSocket server = new ServerSocket(socketPort);
			Socket socket = server.accept();

			// For now, it just prints the socket from the inbound connection
			System.out.println(socket);

			// Closes the connections
			socket.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
