package com.camoga.grapher;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.camoga.grapher.functions.Mandelbrot;
import com.camoga.grapher.functions.Polynomial;

public class Main {
	public Main(int WIDTH, int HEIGHT, IFunction f, int coloring) {
		Window window = new Window(WIDTH, HEIGHT);
//		Polynomial a = new Polynomial(-1,0,1);
//		Polynomial b = new Polynomial(new Complex(-2,-1), new Complex(1,0));
//		Polynomial c = new Polynomial(new Complex(2,2),new Complex(), new Complex(1,0));
//		int[] pixels = Plotter.plot((Complex z) -> Complex.div(Complex.mul(a.evaluate(z), Complex.pow(b.evaluate(z), 2)), c.evaluate(z)), WIDTH, HEIGHT, 0, 0, 10, 0b10, null);

		int[] pixels = Plotter.plot((Complex z) -> Complex.pow(z, new Complex(3,-1)), WIDTH, HEIGHT, 0, 0, 10, coloring);

		saveImage(pixels, WIDTH, HEIGHT);
	}
	
	public static void main(String[] args) {
		new Main(1000,1000, (Complex z) -> Complex.pow(z, z), 0b10);
	}
	
	public void saveImage(int[] pixels, int WIDTH, int HEIGHT) {

		BufferedImage outputimage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		outputimage.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
		
		try {
			ImageIO.write(outputimage, "PNG", new FileOutputStream("C:\\Users\\usuario\\workspace\\MATHS\\Complex-Plane-Grapher\\output\\graph"+System.nanoTime()+".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}