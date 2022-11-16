package server;

import java.io.BufferedWriter;

public interface IModel {

	public void init(BufferedWriter _out) throws Exception;
	public void calc(String _expression) throws Exception;
	
}
