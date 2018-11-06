package client;

import java.util.ArrayList;

public class LaunchClient {

	public static void main(String[] args) {
		ArrayList clients = new ArrayList<Client>();

		// For Debug
		for (int i = 0; i < 10; i++) {
			clients.add(new Client(clients.size()));
		}
	}

}
