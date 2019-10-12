package com.camoga.grapher;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import static com.camoga.complex.Complex.*;

public class Main {
	
	public static void main(String[] args) {
		int WIDTH = 1600, HEIGHT = 1600;
		
		new Window(WIDTH, HEIGHT); // optional
		int[] pixels = Plotter.plot(z -> zeta(z, 100), WIDTH, HEIGHT, -4, -2, 2, 20, Plotter.CONTOURLINES | Plotter.ARGLINES);

		saveImage(pixels, WIDTH, HEIGHT);
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