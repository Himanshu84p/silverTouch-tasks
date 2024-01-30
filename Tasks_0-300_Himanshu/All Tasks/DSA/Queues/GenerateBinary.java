import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GenerateBinary {

    public static ArrayList<String> generate(int n) {

        ArrayList<String> result = new ArrayList<>();
        Queue<String> q = new LinkedList<>();

        q.add("1");
        while (n-- > 0) {
            String temp = q.remove();

            result.add(temp);
            System.out.print(result);
            System.out.println();

            q.add(temp + "0");
            q.add(temp + "1");
        }
        return result;
    }

    public static void main(String[] args) {
        generate(5);
    }
}
