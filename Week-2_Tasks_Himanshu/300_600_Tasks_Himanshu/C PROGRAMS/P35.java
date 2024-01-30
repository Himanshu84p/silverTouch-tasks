import java.math.BigInteger;
import java.security.SecureRandom;

public class P35 {

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    public P35(int bitLength) {
        generateKeys(bitLength);
    }

    public void generateKeys(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = new BigInteger(bitLength, 100, random);
        BigInteger q = new BigInteger(bitLength, 100, random);

        modulus = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        publicKey = new BigInteger("65537");
        privateKey = publicKey.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    public static void main(String[] args) {
        P35 rsa = new P35(1024);


        BigInteger message = new BigInteger("123456789");


        BigInteger encryptedMessage = rsa.encrypt(message);
        System.out.println("Encrypted Message: " + encryptedMessage);


        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
