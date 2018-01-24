import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Window extends Canvas {
	
	public BufferedImage image;
	public int[] pixels;
	
	public Window() {
		JFrame f = new JFrame("Complex Plotter - by MrCamoga");
		f.setSize(600, 600);
		f.setResizable(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.setVisible(true);
		
		image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		Thread thread = new Thread(() -> run());
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
		g.drawImage(image, 0, 0, null);
		g.dispose();
		buffer.show();
	}
}