package com.camoga.grapher.functions;

import com.camoga.grapher.Complex;
import com.camoga.grapher.IFunction;

public class Mandelbrot implements IFunction {

	private int iterations;
	
	public Mandelbrot(int it) {
		this.iterations = it;
	}
	
	public Complex evaluate(Complex z) {
		Complex it = Complex.add(Complex.pow(z, 2), z);
		for(int i = 0; i < iterations; i++) {
			it = Complex.add(Complex.pow(it, 2), z);
		}
		return it;
	}

	public String toString() {
		return "Mandelbrot Set";
	}
}