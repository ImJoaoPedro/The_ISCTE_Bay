package client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	//TODO thread?

	private static final String IP = "127.0.0.1";
	private Socket socket;

	public Client(int num) {

		System.out.println("I'm the client number " + (num + 1));

		try {
			socket = new Socket(IP, 8080);
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
					true);
			out.println("INSC " + (num + 1) + IP + socket.getLocalPort());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(socket);

	}

}
