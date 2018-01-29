package com.camoga.grapher;
import java.awt.Color;

public class Plotter {
	
	public static int[] pixels;
	public static int yRendering = -1;
	
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
		pixels = new int[width*height];
		
		for(int y = 0; y < height; y++) {
			yRendering = y;
			double yr = (double)(y)/(double)height*scale - ys - scale/2;
			for(int x = 0; x < width; x++) {
				double xr = (double)(x)/(double)width*scale + xs - scale/2;
				Complex z = f.evaluate(new Complex(xr,-yr));
				float hue = (float) (Complex.argument(z)/(2*Math.PI));
				float brightness = (float) (Math.log(Complex.mod(z))%1.01)/1.01f;
//				float brightness = (float) (1-Math.pow(2, -Complex.mod(z)));
				pixels[x+y*width] = Color.HSBtoRGB(hue, 1, brightness);
			}
		}
		
		return pixels;
	}
}