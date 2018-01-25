package com.camoga.grapher;

import com.camoga.grapher.functions.Polynomial;

public class Main {
	public Main() {
		Window window = new Window();
		Polynomial f = new Polynomial(-1,-2,10,7,-1);
		int[] pixels = Plotter.plot((Complex z)-> Complex.atan(Complex.add(Complex.mul(z, z),new Complex(6,0))), 600, 600, 0, 0, 10);
		for(int i = 0; i < window.pixels.length; i++) {
			window.pixels[i] = pixels[i]; 
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
}