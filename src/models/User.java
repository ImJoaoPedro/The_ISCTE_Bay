package models;

import java.net.Socket;

public class User {

	private String address;
	private int port;
	private Socket socket;

	public User(String address, int port, Socket socket) {
		this.address = address;
		this.port = port;
		this.socket = socket;
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public Socket getSocket() {
		return socket;
	}

}
