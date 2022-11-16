package compute;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Computing {

	public static String StrCompute (String _expresson) throws Exception {
		
		if(_expresson.contains("/0") && !_expresson.contains("/0.")) throw new Exception("Dividing by zero\n");
		
		if(_expresson.isEmpty()) return _expresson;
		
		DoubleEvaluator eval = new DoubleEvaluator();
		String expresson = eval.evaluate(_expresson).toString();
		
		if(expresson.contentEquals("NaN")) throw new Exception("The answer is NaN\n");
		
		return expresson;
	}
	
}
