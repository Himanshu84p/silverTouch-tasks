import java.util.*;

public class p72 {
    // Function to perform FFT on the given complex numbers
    public static Complex[] fft(Complex[] a) {
        int n = a.length;
        if (n == 1) {
            return new Complex[] { a[0] };
        }

        Complex[] even = new Complex[n / 2];
        Complex[] odd = new Complex[n / 2];
        for (int i = 0; i < n / 2; i++) {
            even[i] = a[i * 2];
            odd[i] = a[i * 2 + 1];
        }

        Complex[] q = fft(even);
        Complex[] r = fft(odd);

        Complex[] y = new Complex[n];
        for (int i = 0; i < n / 2; i++) {
            double t = -2 * i * Math.PI / n;
            Complex omega = new Complex(Math.cos(t), Math.sin(t));
            y[i] = q[i].plus(omega.times(r[i]));
            y[i + n / 2] = q[i].minus(omega.times(r[i]));
        }
        return y;
    }

    // Function to perform inverse FFT
    public static Complex[] ifft(Complex[] a) {
        int n = a.length;
        Complex[] aConj = new Complex[n];
        for (int i = 0; i < n; i++) {
            aConj[i] = a[i].conjugate();
        }

        Complex[] y = fft(aConj);

        for (int i = 0; i < n; i++) {
            y[i] = y[i].conjugate().times(1.0 / n);
        }

        return y;
    }

    public static void main(String[] args) {
        Complex[] a = { new Complex(1, 0), new Complex(2, 0), new Complex(3, 0), new Complex(4, 0) };
        Complex[] result = fft(a);

        System.out.println("FFT Result:");
        for (Complex c : result) {
            System.out.println(c);
        }

        Complex[] inverseResult = ifft(result);

        System.out.println("\nInverse FFT Result:");
        for (Complex c : inverseResult) {
            System.out.println(c);
        }
    }
}

class Complex {
    private final double real;
    private final double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex plus(Complex b) {
        double real = this.real + b.real;
        double imag = this.imag + b.imag;
        return new Complex(real, imag);
    }

    public Complex minus(Complex b) {
        double real = this.real - b.real;
        double imag = this.imag - b.imag;
        return new Complex(real, imag);
    }

    public Complex times(Complex r) {
        double real = this.real * r.real - this.imag * r.imag;
        double imag = this.real * r.imag + this.imag * r.real;
        return new Complex(real, imag);
    }

    public Complex conjugate() {
        return new Complex(real, -imag);
    }

    public String toString() {
        return "(" + real + ", " + imag + ")";
    }
}
