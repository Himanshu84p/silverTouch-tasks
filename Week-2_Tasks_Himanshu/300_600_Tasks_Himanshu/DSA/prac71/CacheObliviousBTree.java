package prac71;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CacheObliviousBTreeNode {
    List<Integer> keys;
    List<CacheObliviousBTreeNode> children;

    public CacheObliviousBTreeNode() {
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }
}

public class CacheObliviousBTree {
    private CacheObliviousBTreeNode root;
    private int degree;

    public CacheObliviousBTree(int degree) {
        this.root = new CacheObliviousBTreeNode();
        this.degree = degree;
    }

    public void insert(int key) {
        CacheObliviousBTreeNode r = root;
        if (r.keys.size() == (2 * degree - 1)) {
            CacheObliviousBTreeNode s = new CacheObliviousBTreeNode();
            root = s;
            s.children.add(r);
            splitChild(s, 0);
            insertNonFull(s, key);
        } else {
            insertNonFull(r, key);
        }
    }

    private void insertNonFull(CacheObliviousBTreeNode x, int key) {
        int i = x.keys.size() - 1;
        if (x.children.isEmpty()) {
            while (i >= 0 && key < x.keys.get(i)) {
                i--;
            }
            x.keys.add(i + 1, key);
        } else {
            while (i >= 0 && key < x.keys.get(i)) {
                i--;
            }
            i++;
            if (x.children.get(i).keys.size() == (2 * degree - 1)) {
                splitChild(x, i);
                if (key > x.keys.get(i)) {
                    i++;
                }
            }
            insertNonFull(x.children.get(i), key);
        }
    }

    private void splitChild(CacheObliviousBTreeNode x, int i) {
        CacheObliviousBTreeNode y = x.children.get(i);
        CacheObliviousBTreeNode z = new CacheObliviousBTreeNode();
        x.children.add(i + 1, z);
        x.keys.add(i, y.keys.get(degree - 1));
        z.keys.addAll(y.keys.subList(degree, 2 * degree - 1));
        y.keys.subList(degree - 1, 2 * degree - 1).clear();
        if (!y.children.isEmpty()) {
            z.children.addAll(y.children.subList(degree, 2 * degree));
            y.children.subList(degree, 2 * degree).clear();
        }
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(CacheObliviousBTreeNode x, int key) {
        int i = 0;
        while (i < x.keys.size() && key > x.keys.get(i)) {
            i++;
        }
        if (i < x.keys.size() && key == x.keys.get(i)) {
            return true;
        } else if (x.children.isEmpty()) {
            return false;
        } else {
            return search(x.children.get(i), key);
        }
    }

    public static void main(String[] args) {
        CacheObliviousBTree tree = new CacheObliviousBTree(2);

        for (int key : new int[] { 3, 1, 4, 2, 5, 6 }) {
            tree.insert(key);
        }

        for (int key : new int[] { 2, 7, 1, 5 }) {
            System.out.println("Key " + key + " found: " + tree.search(key));
        }
    }
}
