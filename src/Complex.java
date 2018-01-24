
import static java.lang.Math.*;

public class Complex {
	private double r, i;
	
	public Complex() {
		this(0,0);
	}
	
	public Complex(double r, double i) {
		this.r = r;
		this.i = i;
	}
	
	public static Complex add(Complex w, Complex z) {
		return new Complex(w.r+z.r,w.i+z.i);
	}
	
	public static Complex sub(Complex w, Complex z) {
		return new Complex(w.r-z.r,w.i-z.i);
	}
	
	public static Complex mul(Complex w, Complex z) {
		return new Complex(w.r*z.r-w.i*z.i,w.r*z.i+z.r*w.i);
	}
	
	public static Complex div(Complex w, Complex z) {
		return div(mul(w, conjugate(z)), modSq(z));
	}
	
	public static Complex div(Complex z, double d) {
		return new Complex(z.r/d, z.i/d);
	}
	
	public static Complex conjugate(Complex z) {
		return new Complex(z.r,-z.i);
	}
	
	public static double modSq(Complex z) {
		return z.r*z.r+z.i*z.i;
	}
	
	public static Complex euler(double phi) {
		return new Complex(cos(phi), sin(phi));
	}
	
	public static double argument(Complex z) {
		return atan2(z.i, z.r);
	}
	
	public String toString() {
		return (r) + (i >= 0 ? (i == 0 ? (""):(" + " + i+"i")):(" - " + i*-1+"i"));
	}

	public static double mod(Complex z) {
		return sqrt(modSq(z));
	}
}