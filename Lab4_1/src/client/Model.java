package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Model implements IModel {

	private Socket clientSocket;
	private BufferedReader in;
	private BufferedWriter out;
	
	@Override
	public void init(Socket _socket, BufferedReader _reader) throws Exception {
		clientSocket = _socket;
		//Stream from server
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//Stream to server
		out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
	}
	
	@Override
	public void send(String _expresion) throws Exception {
		out.write(_expresion + "\n");
		out.flush();
		System.out.println(in.readLine());
	}
	
	@Override
	public void onClose() throws Exception {
		clientSocket.close();
		in.close();
		out.close();
	}
	
}