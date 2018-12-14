public class Complex {

	public double real;
	public double imag;

	public Complex() {
		this.real = 0.;
		this.imag = 0.;
	}

	public Complex(double r) {
		this.real = r;
		this.imag = 0.;
	}

	public Complex(double r, double i) {
		this.real = r;
		this.imag = i;
	}

	public double getReal() {
		return this.real;
	}

	public double getImag() {
		return this.imag;
	}

	public void initialize() {
		
	}
}