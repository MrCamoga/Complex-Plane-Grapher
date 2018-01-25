package com.camoga.grapher;
import java.awt.Color;

public class Plotter {
	
	/**
	 * 
	 * @param f
	 * @param width of the window
	 * @param height of the window
	 * @param xs centered at x
	 * @param ys centered at iy
	 * @param scale plot width and height in units
	 * @return
	 */
	public static int[] plot(IFunction f, int width, int height, double xs, double ys, double scale) {
		int[] pixels = new int[width*height];
		
		for(int y = 0; y < height; y++) {
			double yr = (double)(y)/(double)height*scale - ys - scale/2;
			for(int x = 0; x < width; x++) {
				double xr = (double)(x)/(double)width*scale - xs - scale/2;
				Complex z = f.evaluate(new Complex(xr,-yr));
				float hue = (float) (Complex.argument(z)/(2*Math.PI));
				float brightness = (float) (1-Math.pow(2, -10*Complex.mod(z)));
				
				pixels[x+y*width] = Color.HSBtoRGB(hue, 1, brightness);
			}
		}
		
		return pixels;
	}
}