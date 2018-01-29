package com.camoga.grapher;

import com.camoga.grapher.functions.Mandelbrot;
import com.camoga.grapher.functions.Polynomial;

public class Main {
	public Main(int WIDTH, int HEIGHT) {
		int[] pixels = Plotter.plot(new Mandelbrot(100), WIDTH, HEIGHT, -0.16, 1.02, .05);
		Window window = new Window(WIDTH, HEIGHT);
		Polynomial f = new Polynomial(-1,-2,10,7,-1);
		
	}
	
	public static void main(String[] args) {
		new Main(900,900);
	}
}