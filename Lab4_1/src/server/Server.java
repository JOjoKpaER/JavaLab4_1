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
	private IModel model;
	private Socket clientSocket;
	private ServerSocket server;
	private BufferedReader in;
	private BufferedWriter out;
	
	public Server(int _port) {
		init(_port);
	}
	
	private void init(int _port) {
		System.out.println("Serever init");
		try {
			server = new ServerSocket(_port);
			clientSocket = server.accept();
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		model = new ModelFactory().createInstance();
		try {
			model.init(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reciveLoop();
	}
	
	private void reciveLoop() {
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
	
	private void onClose() {
		try {
			clientSocket.close();
			in.close();
	        out.close();
	        server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
