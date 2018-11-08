package models;

import java.net.Socket;

public class User {

	private String name;
	private String address;
	private int port;
	private Socket socket;

	public User(String name, String address, int port, Socket socket) {
		this.name = name;
		this.address = address;
		this.port = port;
		this.socket = socket;
	}

	public String getName() {
		return name;
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

	
//	public String toString() {
//		return getAddress() + " " + getPort();
//	}

}
