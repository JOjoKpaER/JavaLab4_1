package client;

import java.io.BufferedReader;
import java.net.Socket;

public interface IModel {

	public void init(Socket _socket, BufferedReader _reader) throws Exception;
	public void send(String _expresion) throws Exception; 
	public void onClose() throws Exception;
	
}
