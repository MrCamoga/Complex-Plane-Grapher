package com.camoga.grapher;

import static java.lang.Math.*;

public class Complex {
	
	public static final Complex ONE = new Complex(1,0);
	public static final Complex I = new Complex(0,1);
	
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
	
	public static Complex ln(Complex z) {
		return new Complex(log(mod(z)),argument(z));
	}
	
	/**
	 * e^iphi = cos(phi) + isin(phi)
	 * @param phi
	 * @return
	 */
	public static Complex euler(double phi) {
		return new Complex(Math.cos(phi), Math.sin(phi));
	}
	
	/**
	 * e^iz
	 * @param z
	 * @return
	 */
	public static Complex euler(Complex z) {
		Complex iz = mul(z, I);
		return exp(iz);
	}
	
	/**
	 * e^z
	 * @param z
	 * @return
	 */
	public static Complex exp(Complex z) {
		return mul(valueOf(Math.exp(z.r)), euler(z.i));
	}
	
	public static Complex cos2(Complex z) {
		Complex eiz = euler(z);
		Complex eiz2 = reciprocal(eiz);
		return mul(add(eiz, eiz2), valueOf(0.5));
	}
	
	public static Complex cos(Complex z) {
		return new Complex(Math.cos(z.r)*Math.cosh(z.i), -Math.sin(z.r)*Math.sinh(z.i));
	}
	
	public static Complex sin(Complex z) {
		return new Complex(Math.sin(z.r)*Math.cosh(z.i), Math.cos(z.r)*Math.sinh(z.i));
	}
	
	public static Complex tan(Complex z) {
		return div(sin(z), cos(z));
	}
	
	public static Complex cosh(Complex z) {
		return cos(mul(I, z));
	}

	public static Complex sinh(Complex z) {
		return mul(new Complex(0,-1), sin(mul(I, z)));
	}
	
	public static Complex tanh(Complex z) {
		return mul(new Complex(0,-1), tan(mul(I, z)));
	}
	
	public static Complex acos(Complex z) {
		return sub(valueOf(PI/2), asin(z));
		//return mul(new Complex(0,-1), ln(add(z, sqrt(sub(pow(z,valueOf(2)), ONE)))));
	}
	
	public static Complex asin(Complex z) {
		return mul(new Complex(0,-1), ln(add(mul(z,I), sqrt(sub(ONE, pow(z,2))))));
	}
	
	public static Complex atan(Complex z) {
		return mul(valueOf(0.5), mul(I, sub(ln(sub(ONE,mul(I, z))), ln(add(ONE, mul(I, z))))));
	}
	
	public static Complex asec(Complex z) {
		return acos(reciprocal(z));
	}
	
	public static Complex acsc(Complex z) {
		return asin(reciprocal(z));
	}
	
	public static Complex acot(Complex z) {
		return atan(reciprocal(z));
	}
	
	public static Complex pow(Complex z, double real) {
		return pow(z, valueOf(real));
	}
	
	public static Complex pow(Complex z, Complex w) {
		double modz = mod(z);
		double argz = argument(z);
		// (r*e^ia)^(c+di)= r^c*e^(iac) * e^(ln(r)*di)*e^(-ad)
		//real product
		double real = Math.pow(modz, w.r)*Math.exp(-argz*w.i);
		Complex c = euler(Math.log(modz)*w.i+argz*w.r);
		return mul(c, valueOf(real));
	}
	
	public static Complex sqrt(Complex z) {
		return pow(z, 0.5);
	}
	
	public static Complex reciprocal(Complex z) {
		return div(ONE, z);
	}
	
	public static Complex valueOf(double r) {
		return new Complex(r,0);
	}
	
	public static double argument(Complex z) {
		return atan2(z.i, z.r);
	}
	
	public String toString() {
		return (r) + (i >= 0 ? (i == 0 ? (""):(" + " + i+"i")):(" - " + i*-1+"i"));
	}

	public static double mod(Complex z) {
		return Math.sqrt(modSq(z));
	}
	
	public double real() {
		return r;
	}
	
	public double imaginary() {
		return i;
	}

	public static Complex cbrt(Complex z) {
		return pow(z, 1/3D);
	}
}