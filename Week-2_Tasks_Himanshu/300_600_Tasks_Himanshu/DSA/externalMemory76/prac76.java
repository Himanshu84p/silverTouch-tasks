package externalMemory76;

import java.io.*;
import java.util.*;

public class prac76 {
    private static final int ORDER = 4;
    private Node root;

    static abstract class Node {
        int keyCount;

        abstract int getKey(int index);
    }

    static class InternalNode extends Node {
        Node[] children;

        InternalNode(int keyCount) {
            this.keyCount = keyCount;
            this.children = new Node[ORDER];
        }

        @Override
        int getKey(int index) {
            return 0;
        }
    }

    static class LeafNode extends Node {
        int[] keys;
        String[] values;

        LeafNode(int keyCount) {
            this.keyCount = keyCount;
            this.keys = new int[ORDER];
            this.values = new String[ORDER];
        }

        @Override
        int getKey(int index) {
            return keys[index];
        }
    }

    public void insert(int key, String value) {
    }

    public String search(int key) {
        return null;
    }

    public static void main(String[] args) {
        prac76 bPlusTree = new prac76();

        // Insert data
        bPlusTree.insert(1, "Data1");
        bPlusTree.insert(2, "Data2");
        bPlusTree.insert(3, "Data3");

        // Search data
        String result = bPlusTree.search(2);
        System.out.println("Search Result: " + result);
    }
}
