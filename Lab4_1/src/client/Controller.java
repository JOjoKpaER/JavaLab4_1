package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class Controller {

	private IModel model;
	public boolean onCloseCondition = false;
	private BufferedReader reader;
	private String localhost; 
	private int port;
	
	public Controller(String _localhost, int _port) {
		localhost = _localhost;
		port = _port;
		init();
	}
	
	private void init() {
		System.out.println("Client init");
		//System.in stream
		reader = new BufferedReader(new InputStreamReader(System.in));
		loop();
	}
	
	private void loop() {
		while (true) {
			Socket socket = null;
			try {
				socket = new Socket(localhost, port);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
				continue;
			} catch (IOException e1) {
				e1.printStackTrace();
				continue;
			}
			System.out.println("Connected to the server");
			model = new ModelFactory().createInstance();
			try {
				model.init(socket, reader);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			while (true) {
				if (onCloseCondition) {
					onClose();
					break;
				}
				try {
					model.send(reader.readLine());
				} catch (Exception e) {
				//	System.out.println(e.getMessage());
					break;
				}
			}
		}
	}
	
	private void onClose() {
		try {
			model.onClose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
