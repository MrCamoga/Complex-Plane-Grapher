package com.camoga.grapher.functions;

import com.camoga.complex.Complex;
import com.camoga.grapher.IFunction;

public class Product implements IFunction {

	private IFunction[] functions;
	
	public Product(IFunction...functions) {
		this.functions = functions;
	}
	
	public Complex evaluate(Complex z) {
		Complex result = Complex.ONE;
		for(IFunction f : functions) Complex.mul(result, f.evaluate(z));
		return result;
	}
	
	public String toString() {
		String s = "";
		for(IFunction f: functions) s += "("+f+")";
		return s;
	}
}