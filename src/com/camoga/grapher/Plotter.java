package com.camoga.grapher;
import java.awt.Color;

public class Plotter {
	
	public static int[] pixels;
	public static int yRendering = -1;
	
	/**
	 * num of lines = 24/guide
	 */
	private final static double guide = 2;
	/**
	 * thickness
	 */
	private final static double guidelines = .2;
	
	/**
	 * frequency of discontinuities
	 */
	private final static float magnitudmod = 1.5f;
	
	/**
	 * 
	 * @param f
	 * @param width of the window
	 * @param height of the window
	 * @param xs centered at x
	 * @param ys centered at iy
	 * @param scale plot width and height in units
	 * @param coloring 
	 * @return
	 */
	public static int[] plot(IFunction f, int width, int height, double xs, double ys, double scale, int coloring) {
		pixels = new int[width*height];
		
		for(int y = 0; y < height; y++) {
			yRendering = y;
			double yr = (double)(y)/(double)height*scale - ys - scale/2;
			for(int x = 0; x < width; x++) {
				double xr = (double)(x)/(double)width*scale + xs - scale/2;
				Complex z = f.evaluate(new Complex(xr,-yr));
				
				float hue = (float) (Complex.argument(z)/(2*Math.PI));
				float saturation = 1;
				float brightness = 0;
				
				if((coloring & 0b01) > 0) {
					brightness = (float) (Math.abs(Math.log(Complex.mod(z)))%magnitudmod)/magnitudmod;
				} else {
					brightness = (float) (1-Math.pow(2, -Complex.mod(z)));
				}
				
				if((coloring & 0b10) > 0) {
					double mod = Math.abs((Complex.argument(z)/Math.PI*12)%guide);
					if(mod <= guidelines) {
						saturation = (float) (mod/guidelines);
					} else if(guide-mod <= guidelines) {
						saturation = (float) ((guide-mod)/guidelines);
					}						
				}
					
					pixels[x+y*width] = Color.HSBtoRGB(hue, saturation, brightness);
				
			}
		}
		System.err.println("Plotting finished!");
		return pixels;
	}
}