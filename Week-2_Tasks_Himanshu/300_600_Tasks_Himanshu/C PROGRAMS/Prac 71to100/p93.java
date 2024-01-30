import java.math.BigInteger;
import java.util.Random;

public class p93 {
    private BigInteger prime;
    private BigInteger[] coefficients;

    public p93(int numCoefficients, int bitSize) {
        Random random = new Random();
        this.prime = BigInteger.probablePrime(bitSize, random);

        this.coefficients = new BigInteger[numCoefficients];
        for (int i = 0; i < numCoefficients; i++) {
            coefficients[i] = new BigInteger(bitSize, random);
        }
    }

    public int hash(String key) {
        BigInteger hashValue = BigInteger.ZERO;

        for (int i = 0; i < coefficients.length; i++) {
            BigInteger coefficient = coefficients[i];
            int charValue = (i < key.length()) ? key.charAt(i) : 0;

            BigInteger term = coefficient.multiply(BigInteger.valueOf(charValue));
            hashValue = hashValue.add(term);
        }

        return hashValue.mod(prime).intValue();
    }

    public static void main(String[] args) {
        p93 hashFunction = new p93(5, 32);

        String key1 = "hello";
        String key2 = "world";

        int hash1 = hashFunction.hash(key1);
        int hash2 = hashFunction.hash(key2);

        System.out.println("Hash of '" + key1 + "': " + hash1);
        System.out.println("Hash of '" + key2 + "': " + hash2);
    }
}
