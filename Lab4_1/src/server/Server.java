package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public boolean onCloseCondition = false;
	private Socket clientSocket;
	private ServerSocket server;
	private BufferedReader in;
	private BufferedWriter out;
	
	private int port;
	
	public Server(int _port) {
		port = _port;
		init();
	}
	
	private void init() {
		System.out.println("Serever init");
		reciveLoop();
	}
	
	private void reciveLoop() {
		while (true) {
			try {
				server = new ServerSocket(port);
				clientSocket = server.accept();
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			} catch (IOException e1) {
				System.out.println("Couldn't the set socket in accept state");
				e1.printStackTrace();
				onClose();
				break;
			}
			IModel model = new ModelFactory().createInstance();
			try {
				model.init(out);
			} catch (Exception e) {
				e.printStackTrace();
			}
			while (true) {
				try {
					model.calc(in.readLine());
				} catch (Exception e) {
					//e.printStackTrace();
					onCloseCondition = true;
				}
				if(onCloseCondition) {
					onClose();
					break;
				}
			}
		}
	}
	
	private void onClose() {
		try {
			clientSocket.close();
			in.close();
	        out.close();
	        server.close();
		} catch (IOException e) {
			System.out.println("Couldn't close the socket");
			e.printStackTrace();
		}
	}
	
}
