import java.util.LinkedList;
import java.util.Queue;

class BTreeNode {
    int[] keys;
    BTreeNode[] children;
    int numKeys;
    boolean isLeaf;

    public BTreeNode(int t, boolean isLeaf) {
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.numKeys = 0;
        this.isLeaf = isLeaf;
    }
}

public class Q35 {
    private BTreeNode root;
    private int t;

    public Q35(int t) {
        this.root = null;
        this.t = t;
    }

    public void insert(int key) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.numKeys = 1;
        } else {
            if (root.numKeys == 2 * t - 1) {
                BTreeNode newRoot = new BTreeNode(t, false);
                newRoot.children[0] = root;
                splitChild(newRoot, 0);
                root = newRoot;
            }
            insertNonFull(root, key);
        }
    }

    private void insertNonFull(BTreeNode x, int key) {
        int i = x.numKeys - 1;

        if (x.isLeaf) {
            while (i >= 0 && key < x.keys[i]) {
                x.keys[i + 1] = x.keys[i];
                i--;
            }
            x.keys[i + 1] = key;
            x.numKeys++;
        } else {
            while (i >= 0 && key < x.keys[i]) {
                i--;
            }

            i++;
            if (x.children[i].numKeys == 2 * t - 1) {
                splitChild(x, i);
                if (key > x.keys[i]) {
                    i++;
                }
            }
            insertNonFull(x.children[i], key);
        }
    }

    private void splitChild(BTreeNode x, int i) {
        BTreeNode y = x.children[i];
        BTreeNode z = new BTreeNode(t, y.isLeaf);
        x.children[i + 1] = z;

        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }
        y.numKeys = t - 1;
        z.numKeys = t - 1;

        if (!y.isLeaf) {
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }

        for (int j = x.numKeys - 1; j >= i; j--) {
            x.keys[j + 1] = x.keys[j];
        }
        x.keys[i] = y.keys[t - 1];
        x.numKeys++;
    }

    private int findKeyIndex(BTreeNode x, int key) {
        int index = 0;
        while (index < x.numKeys && key > x.keys[index]) {
            index++;
        }
        return index;
    }

    public void delete(int key) {
        if (root != null) {
            delete(root, key);

            if (root.numKeys == 0) {
                if (root.isLeaf) {
                    root = null;
                } else {
                    root = root.children[0];
                }
            }
        }
    }

    private void delete(BTreeNode x, int key) {
        int index = findKeyIndex(x, key);

        if (index < x.numKeys && x.keys[index] == key) {
            if (x.isLeaf) {
                removeFromLeaf(x, index);
            } else {
                removeFromNonLeaf(x, index);
            }
        } else {
            if (x.isLeaf) {
                System.out.println("Key " + key + " not found in the tree.");
                return;
            }

            boolean lastChild = (index == x.numKeys);

            if (x.children[index].numKeys < t) {
                fill(x, index);
            }

            if (lastChild && index > x.numKeys) {
                delete(x.children[index - 1], key);
            } else {
                delete(x.children[index], key);
            }
        }
    }

    private void removeFromLeaf(BTreeNode x, int index) {
        for (int i = index + 1; i < x.numKeys; i++) {
            x.keys[i - 1] = x.keys[i];
        }
        x.numKeys--;
    }

    private void removeFromNonLeaf(BTreeNode x, int index) {
        int key = x.keys[index];

        if (x.children[index].numKeys >= t) {
            int predecessor = getPredecessor(x, index);
            x.keys[index] = predecessor;
            delete(x.children[index], predecessor);
        } else if (x.children[index + 1].numKeys >= t) {
            int successor = getSuccessor(x, index);
            x.keys[index] = successor;
            delete(x.children[index + 1], successor);
        } else {
            merge(x, index);
            delete(x.children[index], key);
        }
    }

    private int getPredecessor(BTreeNode x, int index) {
        BTreeNode current = x.children[index];
        while (!current.isLeaf) {
            current = current.children[current.numKeys];
        }
        return current.keys[current.numKeys - 1];
    }

    private int getSuccessor(BTreeNode x, int index) {
        BTreeNode current = x.children[index + 1];
        while (!current.isLeaf) {
            current = current.children[0];
        }
        return current.keys[0];
    }

    private void fill(BTreeNode x, int index) {
        if (index != 0 && x.children[index - 1].numKeys >= t) {
            borrowFromPrev(x, index);
        } else if (index != x.numKeys && x.children[index + 1].numKeys >= t) {
            borrowFromNext(x, index);
        } else {
            if (index != x.numKeys) {
                merge(x, index);
            } else {
                merge(x, index - 1);
            }
        }
    }

    private void borrowFromPrev(BTreeNode x, int index) {
        BTreeNode child = x.children[index];
        BTreeNode sibling = x.children[index - 1];

        for (int i = child.numKeys - 1; i >= 0; i--) {
            child.keys[i + 1] = child.keys[i];
        }

        if (!child.isLeaf) {
            for (int i = child.numKeys; i >= 0; i--) {
                child.children[i + 1] = child.children[i];
            }
        }

        child.keys[0] = x.keys[index - 1];

        if (!x.isLeaf) {
            child.children[0] = sibling.children[sibling.numKeys];
        }

        x.keys[index - 1] = sibling.keys[sibling.numKeys - 1];

        child.numKeys++;
        sibling.numKeys--;
    }

    private void borrowFromNext(BTreeNode x, int index) {
        BTreeNode child = x.children[index];
        BTreeNode sibling = x.children[index + 1];

        child.keys[child.numKeys] = x.keys[index];

        if (!child.isLeaf) {
            child.children[child.numKeys + 1] = sibling.children[0];
        }

        x.keys[index] = sibling.keys[0];

        for (int i = 1; i < sibling.numKeys; i++) {
            sibling.keys[i - 1] = sibling.keys[i];
        }

        if (!sibling.isLeaf) {
            for (int i = 1; i <= sibling.numKeys; i++) {
                sibling.children[i - 1] = sibling.children[i];
            }
        }

        child.numKeys++;
        sibling.numKeys--;
    }

    private void merge(BTreeNode x, int index) {
        BTreeNode child = x.children[index];
        BTreeNode sibling = x.children[index + 1];

        child.keys[t - 1] = x.keys[index];

        for (int i = 0; i < sibling.numKeys; i++) {
            child.keys[i + t] = sibling.keys[i];
        }

        if (!child.isLeaf) {
            for (int i = 0; i <= sibling.numKeys; i++) {
                child.children[i + t] = sibling.children[i];
            }
        }

        for (int i = index + 1; i < x.numKeys; i++) {
            x.keys[i - 1] = x.keys[i];
        }

        for (int i = index + 2; i <= x.numKeys; i++) {
            x.children[i - 1] = x.children[i];
        }

        child.numKeys += sibling.numKeys + 1;
        x.numKeys--;
    }

    public void print() {
        if (root != null) {
            print(root, 0);
        }
    }

    private void print(BTreeNode node, int level) {
        System.out.println("Level " + level + ": " + node.numKeys + " keys " + (node.isLeaf ? "(Leaf)" : ""));

        for (int i = 0; i < node.numKeys; i++) {
            System.out.print(node.keys[i] + " ");
        }
        System.out.println();

        if (!node.isLeaf) {
            for (int i = 0; i <= node.numKeys; i++) {
                if (node.children[i] != null) {
                    print(node.children[i], level + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Q35 bTree = new Q35(3);

        bTree.insert(1);
        bTree.insert(3);
        bTree.insert(7);
        bTree.insert(10);
        bTree.insert(11);
        bTree.insert(13);
        bTree.insert(14);
        bTree.insert(15);
        bTree.insert(18);
        bTree.insert(16);
        bTree.insert(19);
        bTree.insert(24);
        bTree.insert(25);
        bTree.insert(26);
        bTree.insert(21);
        bTree.insert(4);
        bTree.insert(5);
        bTree.insert(20);
        bTree.insert(22);
        bTree.insert(2);

        System.out.println("B-tree after insertions:");
        bTree.print();

        bTree.delete(24);
        System.out.println("\nB-tree after deletion of key 24:");
        bTree.print();
    }
}
