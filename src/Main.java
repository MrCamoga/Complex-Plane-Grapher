
public class Main {
	public Main() {
		Window window = new Window();
		Polynomial f = new Polynomial(-1,-2,10,7,-1);
		int[] pixels = Plotter.plot(f, 600, 600, 0, 0, 4);
		for(int i = 0; i < window.pixels.length; i++) {
			window.pixels[i] = pixels[i]; 
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
}