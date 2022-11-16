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

class Calc extends Thread{
	
	private String expression;
	private	BufferedWriter out;
	
	public Calc(String _expression, BufferedWriter _out) {
		expression = _expression;
		out = _out;
	}
	
	@Override
	public void run() {
		String result;
		try {
			result = compute.Computing.StrCompute(expression);
		} catch (Exception e1) {
			result = e1.getMessage();
		}
		try {
			out.write(result + "\n");
			out.flush();
		} catch (IOException e) {
			System.out.println("Out stream for calculator closed");
		}
	}
}
