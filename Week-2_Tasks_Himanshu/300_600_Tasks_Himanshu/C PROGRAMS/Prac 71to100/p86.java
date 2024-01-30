public class p86 {
    private long seed;
    private long multiplier;
    private long increment;
    private long modulus;

    public p86(long seed, long multiplier, long increment, long modulus) {
        this.seed = seed;
        this.multiplier = multiplier;
        this.increment = increment;
        this.modulus = modulus;
    }

    public p86() {    
        this(0, 1664525, 1013904223, (long) Math.pow(2, 32));
    }

    public long nextRandom() {
        seed = (multiplier * seed + increment) % modulus;
        return seed;
    }

    public static void main(String[] args) {
        p86 lcg = new p86();
    
        System.out.println("Generated Pseudorandom Numbers:");
        for (int i = 0; i < 10; i++) {
            System.out.println(lcg.nextRandom());
        }
    }
}
