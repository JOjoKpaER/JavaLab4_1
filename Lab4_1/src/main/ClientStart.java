package main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStart {

	public static void main(String[] args) {
		try {
			client.Controller c = new client.Controller(new Socket("localhost", 4004));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
