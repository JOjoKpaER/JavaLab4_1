package main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStart {

	public static void main(String[] args) {
		client.Controller c = new client.Controller("localhost", 6969);
	}

}
