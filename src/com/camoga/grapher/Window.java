package com.camoga.grapher;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Window extends Canvas implements Runnable {
	
	public BufferedImage image;
	public int[] pixels;
	public int SCALE = 4;
	
	public Window(int WIDTH, int HEIGHT) {
		JFrame f = new JFrame("Complex Plotter - by MrCamoga");
		f.setSize(800, 800);
		SCALE = 800/WIDTH;
		f.setResizable(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.setVisible(true);
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		double last = System.nanoTime();
		
		double ns = 1e9/60D;
		
		double delta = 0;
		
		while(true) {
			double now = System.nanoTime();
			delta += (now-last)/ns;
			last = now;
			
			while(delta >= 1) {
				delta--;
				render();
			}
		}
	}
	
	public void render() {
		BufferStrategy buffer = getBufferStrategy();
		if(buffer == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = Plotter.pixels[i];
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		if(Plotter.yRendering != -1) {
			g.setColor(Color.red);
			g.drawLine(0, Plotter.yRendering*SCALE, image.getWidth()*SCALE, Plotter.yRendering*SCALE);
		}
		g.dispose();
		buffer.show();
	}
}