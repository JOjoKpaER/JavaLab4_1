package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Controller {

	private IModel model;
	public boolean onCloseCondition = false;
	private BufferedReader reader;
	
	public Controller(Socket _socket) {
		init(_socket);
	}
	
	private void init(Socket _socket) {
		System.out.println("Client init");
		model = new ModelFactory().createInstance();
		//System.in stream
		reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			model.init(_socket, reader);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		loop();
	}
	
	private void loop() {
		while (true) {
			if (onCloseCondition) {
				onClose();
				break;
			}
			
			try {
				model.send(reader.readLine());
			} catch (Exception e) {
				System.out.println(e.getMessage());
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
