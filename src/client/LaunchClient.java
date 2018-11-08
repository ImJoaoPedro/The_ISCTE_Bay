package client;

import java.util.ArrayList;

public class LaunchClient {

	public static void main(String[] args) {
		ArrayList<Client> clients = new ArrayList<Client>();

		// launch dummy client
		clients.add(new Client(clients.size()));
		for (Client c : clients) {
			c.launchClient();
		}
	}

}
