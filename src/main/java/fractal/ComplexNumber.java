package fractal;

import java.lang.Math;

public class ComplexNumber {

	private final double _real;
	private final double _imag;

	public ComplexNumber(double real, double imag) {
		_real = real;
		_imag = imag;
	}

	public double getReal() { return _real; }
	public double getImag() { return _imag; }

	public double abs() { return Math.sqrt(Math.pow(_real,2) + Math.pow(_imag,2)); }

	public ComplexNumber add(ComplexNumber n) {
		double real = _real + n.getReal();
		double imag = _imag + n.getImag();
		return new ComplexNumber(real, imag);
	}

	public ComplexNumber multiply(ComplexNumber n) {
		// (a + bi)*(c + di) = a*c + a*di + bi*c - b*d
		// = (a*c - b*d) + (a*d + b*c)i 
		double real = _real * n.getReal() - _imag * n.getImag();
		double imag = _real * n.getImag() + _imag * n.getReal();
		return new ComplexNumber(real, imag);
	}
}
