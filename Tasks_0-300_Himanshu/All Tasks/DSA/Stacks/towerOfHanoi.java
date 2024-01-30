public class towerOfHanoi {

    public static void solvetowerOfHanoi(int n, char src, char aux, char des) {
        if (n == 1) {
            System.out.println(src + " -> " + des);
            return;
        }
        solvetowerOfHanoi(n - 1, src, des, aux);
        solvetowerOfHanoi(1, src, aux, des);
        solvetowerOfHanoi(n - 1, aux, src, des);
    }

    public static void main(String[] args) {
        solvetowerOfHanoi(3, 'A', 'B', 'C');
    }
}
