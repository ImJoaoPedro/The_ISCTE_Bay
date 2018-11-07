package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

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

	public int getClientNum() {
		return num;
	}

	public void launchClient() {
		try {
			joinServer();
		} finally {
			System.out.println("Closing");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void joinServer() {

		int currentnumber = getClientNum() + 1;

		System.out.println("I'm the client number " + (currentnumber));

		try {
			// create socket
			socket = new Socket(BASE_IP, Server.DEFAULT_PORT);

			// recieves
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// sends
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

			out.println("INSC" + (currentnumber) + BASE_IP + socket.getLocalPort());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(socket);

	}

}
