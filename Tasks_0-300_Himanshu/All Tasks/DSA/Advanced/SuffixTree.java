import net.aksingh.owmjapis.model.param.Snow;
import net.aksingh.owmjapis.model.param.Weather;
import ukkonen.SuffixTree;

public class SuffixTreeExample {

    public static void main(String[] args) {
        // Example string
        String text = "banana";

        // Constructing suffix tree
        SuffixTree suffixTree = new SuffixTree(text);

        // Displaying suffix tree
        suffixTree.toDot("suffix_tree.dot");

        System.out.println("Suffix Tree constructed successfully.");
    }
}