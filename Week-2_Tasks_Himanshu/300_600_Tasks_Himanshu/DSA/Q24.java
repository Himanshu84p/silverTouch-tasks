import java.util.Random;

public class Q24 {

    private static int random01() {

        Random random = new Random();
        return random.nextInt(2);
    }

    public static int random06() {
        int result;

        do {

            result = (random01() << 2) | (random01() << 1) | random01();
        } while (result > 6);

        return result;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(random06());
        }
    }
}
