public class Polynomial implements IFunction {
	
	//TODO coefficients can be functions
	public Complex[] coefficients;
	
	//TODO flip around coefficients
	public Polynomial(Complex... z) {
		coefficients = z;
	}
	
	public Polynomial(double... r) {
		coefficients = new Complex[r.length];
		for(int i = 0; i < coefficients.length; i++) {
			coefficients[i] = new Complex(r[i], 0);
		}
	}
	
	public Complex evaluate(Complex z) {
		Complex m = new Complex();
		Complex temp = new Complex(1,0);
		for(Complex c: coefficients) {
			m = Complex.add(m, Complex.mul(c, temp));
			temp = Complex.mul(temp, z);
		}
		return m;
	}
	
	public String toString() {
		return "polynomial";
	}
}