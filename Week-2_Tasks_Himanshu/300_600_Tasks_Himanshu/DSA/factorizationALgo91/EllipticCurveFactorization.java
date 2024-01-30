package factorizationALgo91;

import java.math.BigInteger;

public class EllipticCurveFactorization {

    static class EllipticCurve {
        private BigInteger a;
        private BigInteger b;
        private BigInteger p;

        public EllipticCurve(BigInteger a, BigInteger b, BigInteger p) {
            this.a = a;
            this.b = b;
            this.p = p;
        }

        public boolean isPointOnCurve(BigInteger x, BigInteger y) {
            BigInteger left = y.multiply(y).mod(p);
            BigInteger right = x.pow(3).add(a.multiply(x)).add(b).mod(p);
            return left.equals(right);
        }
    }

    static class Point {
        private BigInteger x;
        private BigInteger y;

        public Point(BigInteger x, BigInteger y) {
            this.x = x;
            this.y = y;
        }

        public BigInteger getX() {
            return x;
        }

        public BigInteger getY() {
            return y;
        }
    }

    public static Point ellipticCurveFactorization(BigInteger n, BigInteger x, BigInteger y, EllipticCurve curve) {
        Point point = new Point(x, y);
        BigInteger k = BigInteger.ONE;

        while (true) {
            point = doublePoint(point, curve);
            k = k.add(BigInteger.ONE);

            if (point == null) {
                return null;
            }

            BigInteger factor = point.getY().gcd(n);

            if (!factor.equals(BigInteger.ONE) && !factor.equals(n)) {
                return new Point(factor, k);
            }
        }
    }

    private static Point doublePoint(Point point, EllipticCurve curve) {
        BigInteger x = point.getX();
        BigInteger y = point.getY();

        BigInteger lambda = x.multiply(BigInteger.valueOf(3)).add(curve.a)
                .multiply(y.multiply(BigInteger.valueOf(2)).modInverse(curve.p))
                .mod(curve.p);

        BigInteger xr = lambda.pow(2).subtract(x.multiply(BigInteger.valueOf(2))).mod(curve.p);
        BigInteger yr = lambda.multiply(x.subtract(xr)).subtract(y).mod(curve.p);

        if (curve.isPointOnCurve(xr, yr)) {
            return new Point(xr, yr);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        BigInteger a = new BigInteger("2");
        BigInteger b = new BigInteger("2");
        BigInteger p = new BigInteger("17");

        EllipticCurve curve = new EllipticCurve(a, b, p);

        BigInteger x = new BigInteger("5");
        BigInteger y = new BigInteger("1");

        BigInteger n = new BigInteger("221");

        Point result = ellipticCurveFactorization(n, x, y, curve);

        if (result != null) {
            System.out.println("Factor found: " + result.getX());
            System.out.println("Iterations: " + result.getY());
        } else {
            System.out.println("Factor not found.");
        }
    }
}
