package models;

import java.io.File;
import java.net.Socket;

public class User {

	private String address;
	private int port;
	private Socket socket;
	private File[] files;
	private String path = System.getProperty("user.dir");

	public User(String address, int port, Socket socket) {
		this.address = address;
		this.port = port;
		this.socket = socket;
		this.files = new File(path + "/res").listFiles();
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

	public File[] getFiles() {
		return files;
	}

}
