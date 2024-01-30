package online_suffixtree90;

import java.util.HashMap;
import java.util.Map;



class Node {
    Map<Character, Edge> edges;
    Node suffixLink;

    public Node() {
        this.edges = new HashMap<>();
        this.suffixLink = null;
    }
}

class Edge {
    int start;
    int end;
    Node target;

    public Edge(int start, int end, Node target) {
        this.start = start;
        this.end = end;
        this.target = target;
    }
}

public class SuffixTree {
    private static final int INFINITY = Integer.MAX_VALUE - 1;
    private Node root;
    private String input;
    private Node activeNode;
    private int activeEdge;
    private int activeLength;
    private int remainder;

    public SuffixTree() {
        this.root = new Node();
        this.activeNode = root;
        this.activeEdge = -1;
        this.activeLength = 0;
        this.remainder = 0;
        this.input = "";
    }

    public void extend(String extension) {
        this.input += extension;
        for (char c : extension.toCharArray()) {
            extendTree(c);
        }
    }

    private void extendTree(char c) {
        remainder++;
        Node lastCreatedNode = null;

        while (remainder > 0) {
            if (activeLength == 0) {
                activeEdge = input.length() - remainder;
            }

            if (!activeNode.edges.containsKey(input.charAt(activeEdge))) {
                Node leaf = new Node();
                activeNode.edges.put(input.charAt(activeEdge), new Edge(input.length() - remainder, INFINITY, leaf));

                if (lastCreatedNode != null) {
                    lastCreatedNode.suffixLink = activeNode;
                    lastCreatedNode = null;
                }
            } else {
                Edge edge = activeNode.edges.get(input.charAt(activeEdge));
                if (walkDown(edge)) {
                    continue;
                }

                if (input.charAt(edge.start + activeLength) == c) {
                    activeLength++;
                    if (lastCreatedNode != null && activeNode != root) {
                        lastCreatedNode.suffixLink = activeNode;
                        lastCreatedNode = null;
                    }
                    break;
                }

                Node split = new Node();
                activeNode.edges.put(input.charAt(activeEdge), new Edge(edge.start, edge.start + activeLength, split));
                split.edges.put(c, new Edge(input.length() - remainder, INFINITY, new Node()));

                edge.start += activeLength;
                split.edges.put(input.charAt(edge.start), edge);

                if (lastCreatedNode != null) {
                    lastCreatedNode.suffixLink = split;
                }

                lastCreatedNode = split;
            }

            remainder--;
            if (activeNode == root && activeLength > 0) {
                activeLength--;
                activeEdge = input.length() - remainder + 1;
            } else if (activeNode != root) {
                activeNode = activeNode.suffixLink != null ? activeNode.suffixLink : root;
            }
        }
    }

    private boolean walkDown(Edge edge) {
        int length = edge.end - edge.start;
        if (activeLength >= length) {
            activeEdge += length;
            activeLength -= length;
            activeNode = edge.target;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree();
        suffixTree.extend("ababab");
        suffixTree.extend("abcabc");

        traverseTree(suffixTree.root, "");


    }

    private static void traverseTree(Node node, String path) {
        for (char edgeChar : node.edges.keySet()) {
            Edge edge = node.edges.get(edgeChar);
            System.out.println(path + " -> (" + edge.start + ", " + edge.end + ")");
            traverseTree(edge.target, path + " -> (" + edge.start + ", " + edge.end + ")");
        }
    }
}
