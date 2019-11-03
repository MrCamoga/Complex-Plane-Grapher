package com.camoga.grapher;
import java.awt.Color;

import com.camoga.complex.Complex;

public class Plotter {
	
	public static int[] pixels;
	public static int yRendering = -1;
	
	/**
	 * num of lines = 24/lines
	 */
	private final static double lines = 2;
	/**
	 * thickness
	 */
	private final static double argumentlines = .2;
	
	/**
	 * frequency of discontinuities
	 */
	private final static float magnitudemod = 1f;
	
	public static final int CONTOURLINES = 1;
	public static final int ARGLINES = 2;
	
	/**
	 * 
	 * @param f
	 * @param width of the window
	 * @param height of the window
	 * @param x0 real axis start
	 * @param y0 imaginary axis start
	 * @param x1 real axis end
	 * @param y1 imaginary axis end
	 * @param coloring Use {@link #CONTOURLINES} {@link #ARGLINES}
	 * @return
	 */
	public static int[] plot(IFunction f, int width, int height, double x0, double y0, double x1, double y1, int coloring) {
		if(pixels == null || pixels.length != width*height)pixels = new int[width*height];
		
		for(int y = 0; y < height; y++) {
			yRendering = y;
			double yr = y1 - (double)(y)/(double)height*(y1-y0);
			for(int x = 0; x < width; x++) {
				double xr = x0 + (double)(x)/(double)width*(x1-x0);
				Complex z = f.evaluate(new Complex(xr,yr));
				
				float hue = (float) (Complex.argument(z)/(2*Math.PI));
				float saturation = 1;
				float brightness = 0;
				
				if((coloring & 0b01) > 0) {
					brightness = (float) ((Math.log(Complex.mod(z))/Math.log(2))%magnitudemod)/magnitudemod;
					if(brightness < 0) brightness += 1;
				} else {
					brightness = (float) (1-Math.pow(2, -Complex.mod(z)));
				}
				
				if((coloring & 0b10) > 0) {
					double mod = Math.abs((Complex.argument(z)/Math.PI*12)%lines);
					if(mod <= argumentlines) {
						saturation = (float) (mod/argumentlines);
					} else if(lines-mod <= argumentlines) {
						saturation = (float) ((lines-mod)/argumentlines);
					}						
				}
					
					pixels[x+y*width] = Color.HSBtoRGB(hue, saturation, brightness);
				
			}
		}
		System.err.println("Plotting finished!");
		return pixels;
	}
	
	public static int HSLtoRGB(float h, float s, float l) {
		float C = (1-Math.abs(2*l-1))*s;
		float Hp = ((h+360)%360)/60.0f;
		float X = C*(1-Math.abs((Hp%2)-1));
		
		float r = 0, g = 0, b = 0;
		
		if(Hp <= 1) {
			r=C; g=X;
		} else if(Hp <= 2) {
			r=X; g=C;
		} else if(Hp <= 3) {
			g=C; b=X;
		} else if(Hp <= 4) {
			g=X; b=C;
		} else if(Hp <= 5) {
			r=X; b=C;
		} else if(Hp < 6) {
			r=C; b=X;
		}
		float m = (l - 0.5f*C);
//		System.out.println((r+m)*0xff+","+(g+m)*0xff+","+(b+m)*0xff);
		
		
		return (0xff << 24) | ((int)((r+m)*0xff)<<16) | ((int)((g+m)*0xff)<<8) | (int)((b+m)*0xff);
	}
}