package models;

public class User {

	private String name;
	private String address;
	private int port;
	
	public User(String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
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
	
	@Override
	public String toString() {
		return "<" + name + ">" + "<" + address + ">" + "<" + port + ">";
	}
	
}
