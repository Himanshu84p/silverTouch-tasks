package prac74;

import java.util.ArrayList;
import java.util.List;

class BTreeNode {
    List<Integer> keys;
    List<BTreeNode> children;

    public BTreeNode() {
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public BTreeNode(List<Integer> keys, List<BTreeNode> children) {
        this.keys = keys;
        this.children = children;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
}

class PersistentBTree {
    private BTreeNode root;
    private List<BTreeNode> versions;

    public PersistentBTree() {
        this.root = new BTreeNode();
        this.versions = new ArrayList<>();
        this.versions.add(root);
    }

    private BTreeNode copyNode(BTreeNode node) {
        List<Integer> keysCopy = new ArrayList<>(node.keys);
        List<BTreeNode> childrenCopy = new ArrayList<>(node.children);

        return new BTreeNode(keysCopy, childrenCopy);
    }

    public void insert(int key) {
        BTreeNode newRoot = copyNode(root);
        if (newRoot.keys.size() == 2 * B - 1) {
            BTreeNode newNode = new BTreeNode();
            newNode.children.add(newRoot);
            splitChild(newNode, 0);
            root = newNode;
            versions.add(newNode);
        }
        insertNonFull(root, key);
    }

    private void insertNonFull(BTreeNode node, int key) {
        int i = node.keys.size() - 1;
        if (node.isLeaf()) {
            while (i >= 0 && key < node.keys.get(i)) {
                i--;
            }
            node.keys.add(i + 1, key);
            versions.add(copyNode(node));
        } else {
            while (i >= 0 && key < node.keys.get(i)) {
                i--;
            }
            i++;
            BTreeNode child = node.children.get(i);
            if (child.keys.size() == 2 * B - 1) {
                splitChild(node, i);
                if (key > node.keys.get(i)) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key);
        }
    }

    private void splitChild(BTreeNode parent, int index) {
        BTreeNode nodeToSplit = parent.children.get(index);
        BTreeNode newNode = new BTreeNode();

        parent.keys.add(index, nodeToSplit.keys.get(B - 1));
        parent.children.add(index + 1, newNode);

        newNode.keys.addAll(nodeToSplit.keys.subList(B, 2 * B - 1));
        nodeToSplit.keys.subList(B - 1, 2 * B - 1).clear();

        if (!nodeToSplit.isLeaf()) {
            newNode.children.addAll(nodeToSplit.children.subList(B, 2 * B));
            nodeToSplit.children.subList(B, 2 * B).clear();
        }

        versions.add(copyNode(parent));
    }

    public boolean search(int key, BTreeNode node) {
        int i = 0;
        while (i < node.keys.size() && key > node.keys.get(i)) {
            i++;
        }

        if (i < node.keys.size() && key == node.keys.get(i)) {
            return true;
        } else if (node.isLeaf()) {
            return false;
        } else {
            return search(key, node.children.get(i));
        }
    }

    public static void main(String[] args) {
        PersistentBTree bTree = new PersistentBTree();
        bTree.insert(3);
        bTree.insert(8);
        bTree.insert(5);
        bTree.insert(1);
        bTree.insert(6);
        bTree.insert(2);
        bTree.insert(7);
        bTree.insert(4);

        System.out.println("Search for key 6: " + bTree.search(6, bTree.root));

        BTreeNode version2 = bTree.versions.get(2);
        System.out.println("Search for key 6 in version 2: " + bTree.search(6, version2));
    }
}
