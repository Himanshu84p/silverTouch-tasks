import java.util.HashMap;
import java.util.Map;

public class p87 {
    static class ConceptNode {
        String label;
        Map<String, ConceptNode> children;

        public ConceptNode(String label) {
            this.label = label;
            this.children = new HashMap<>();
        }
    }

    private ConceptNode root;

    public p87() {
        this.root = new ConceptNode("Root");
    }

    public void learnInstance(String[] instance) {
        addToTree(root, instance, 0);
    }

    private void addToTree(ConceptNode node, String[] instance, int index) {
        if (index == instance.length) {
            return;
        }

        String attributeValue = instance[index];

        if (!node.children.containsKey(attributeValue)) {
            ConceptNode newNode = new ConceptNode(attributeValue);
            node.children.put(attributeValue, newNode);
        }

        addToTree(node.children.get(attributeValue), instance, index + 1);
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(ConceptNode node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(node.label);

        for (ConceptNode child : node.children.values()) {
            printTree(child, depth + 1);
        }
    }

    public static void main(String[] args) {
        p87 cobweb = new p87();

        String[] instance1 = { "Sunny", "Warm", "Normal", "Strong" };
        String[] instance2 = { "Sunny", "Warm", "High", "Strong" };
        String[] instance3 = { "Overcast", "Cool", "High", "Weak" };
        String[] instance4 = { "Rainy", "Mild", "High", "Weak" };

        cobweb.learnInstance(instance1);
        cobweb.learnInstance(instance2);
        cobweb.learnInstance(instance3);
        cobweb.learnInstance(instance4);

        System.out.println("Learned Concept Tree:");
        cobweb.printTree();
    }
}
