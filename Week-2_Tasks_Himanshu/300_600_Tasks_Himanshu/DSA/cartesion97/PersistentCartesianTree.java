package cartesion97;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PersistentCartesianTree {

    static class Node {
        int key;
        int priority;
        Node left, right;

        public Node(int key, int priority) {
            this.key = key;
            this.priority = priority;
            this.left = this.right = null;
        }
    }

    private Node root;
    private Map<Integer, Node> versionMap;

    public PersistentCartesianTree() {
        this.root = null;
        this.versionMap = new HashMap<>();
    }

    public void insert(int key) {
        root = insert(root, key, randomPriority());
    }

    private Node insert(Node node, int key, int priority) {
        if (node == null) {
            return new Node(key, priority);
        }

        if (priority > node.priority) {
            Node[] split = split(node, key);
            Node newNode = new Node(key, priority);
            newNode.left = split[0];
            newNode.right = split[1];
            return newNode;
        } else if (key < node.key) {
            node.left = insert(node.left, key, priority);
        } else {
            node.right = insert(node.right, key, priority);
        }

        return node;
    }

    private Node[] split(Node node, int key) {
        Node[] result = new Node[2];

        if (node == null) {
            return result;
        }

        if (key < node.key) {
            Node[] splitResult = split(node.left, key);
            node.left = splitResult[1];
            result[0] = splitResult[0];
            result[1] = node;
        } else {
            Node[] splitResult = split(node.right, key);
            node.right = splitResult[0];
            result[0] = node;
            result[1] = splitResult[1];
        }

        return result;
    }

    public Node getVersion(int version) {
        return versionMap.get(version);
    }

    private int randomPriority() {
        return new Random().nextInt();
    }

    public static void main(String[] args) {
        PersistentCartesianTree persistentCartesianTree = new PersistentCartesianTree();

        persistentCartesianTree.insert(5);
        Node version1 = persistentCartesianTree.root;
        persistentCartesianTree.versionMap.put(1, version1);

        persistentCartesianTree.insert(3);
        Node version2 = persistentCartesianTree.root;
        persistentCartesianTree.versionMap.put(2, version2);

        persistentCartesianTree.insert(7);
        Node version3 = persistentCartesianTree.root;
        persistentCartesianTree.versionMap.put(3, version3);

        Node queryResult1 = persistentCartesianTree.getVersion(1);
        Node queryResult2 = persistentCartesianTree.getVersion(2);
        Node queryResult3 = persistentCartesianTree.getVersion(3);

        System.out.println("Version 1: " + queryResult1);
        System.out.println("Version 2: " + queryResult2);
        System.out.println("Version 3: " + queryResult3);
    }
}
