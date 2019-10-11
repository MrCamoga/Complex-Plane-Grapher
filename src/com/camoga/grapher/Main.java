package com.camoga.grapher;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.camoga.complex.Complex;

public class Main {
	
	public static void main(String[] args) {
		int WIDTH = 800, HEIGHT = 800;
		
		new Window(WIDTH, HEIGHT); // optional
		int[] pixels = Plotter.plot(z -> Complex.cosh(Complex.cos(z)), 800, 800, -4, -4, 4, 4, 0b10);

		saveImage(pixels, 800, 800);
	}
	
	public static void saveImage(int[] pixels, int WIDTH, int HEIGHT) {
		BufferedImage outputimage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		outputimage.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
		
		try {
			ImageIO.write(outputimage, "PNG", new FileOutputStream("C:\\Users\\usuario\\workspace\\MATHS\\Complex-Plane-Grapher\\output\\graph"+System.nanoTime()+".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}