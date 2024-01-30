import java.util.Random;

public class Q25 {

    private int selectedNumber;
    private int count;

    public Q25() {
        selectedNumber = 0;
        count = 0;
    }

    public void selectRandomNumber(int newNumber) {
        count++;

        Random random = new Random();
        int randomIndex = random.nextInt(count);

        if (randomIndex == count - 1) {
            selectedNumber = newNumber;
        }
    }

    public int getRandomNumber() {
        return selectedNumber;
    }

    public static void main(String[] args) {
        Q25 randomStream = new Q25();

        int[] stream = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        for (int i = 0; i < stream.length; i++) {
            randomStream.selectRandomNumber(stream[i]);
        }

        System.out.println("Random Number from Stream: " + randomStream.getRandomNumber());
    }
}
