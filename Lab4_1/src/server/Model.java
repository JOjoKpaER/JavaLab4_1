package server;

import java.io.BufferedWriter;
import java.io.IOException;

public class Model implements IModel {

	private	BufferedWriter out;	
	@Override
	public void init(BufferedWriter _out) throws Exception{
		out = _out;
	}
	
	public void calc(String _expression) throws Exception{
		Calc C = new Calc(_expression, out);
		C.start();
	}
		
}

final class Calc extends Thread{
	
	private String expression;
	private	BufferedWriter out;
	
	public Calc(String _expression, BufferedWriter _out) {
		expression = _expression;
		out = _out;
	}
	
	@Override
	public void run() {
		try {
			out.write(expression);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
