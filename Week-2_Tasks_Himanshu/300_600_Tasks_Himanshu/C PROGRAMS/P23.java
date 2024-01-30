import java.util.ArrayList;
import java.util.List;

class BTreeNode {
    private List<Integer> keys;
    private List<BTreeNode> children;
    private boolean leaf;

    public BTreeNode(boolean leaf) {
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.leaf = leaf;
    }

    public List<Integer> getKeys() {
        return keys;
    }

    public List<BTreeNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return leaf;
    }
}

public class P23 {
    private BTreeNode root;
    private int t;

    public P23(int t) {
        this.root = new BTreeNode(true);
        this.t = t;
    }

    public void insert(int key) {
        BTreeNode r = root;
        if (r.getKeys().size() == (2 * t) - 1) {
            BTreeNode s = new BTreeNode(false);
            root = s;
            s.getChildren().add(r);
            splitChild(s, 0);
            insertNonFull(s, key);
        } else {
            insertNonFull(r, key);
        }
    }

    private void insertNonFull(BTreeNode x, int key) {
        int i = x.getKeys().size() - 1;
        if (x.isLeaf()) {
            while (i >= 0 && key < x.getKeys().get(i)) {
                i--;
            }
            x.getKeys().add(i + 1, key);
        } else {
            while (i >= 0 && key < x.getKeys().get(i)) {
                i--;
            }
            i++;
            if (x.getChildren().get(i).getKeys().size() == (2 * t) - 1) {
                splitChild(x, i);
                if (key > x.getKeys().get(i)) {
                    i++;
                }
            }
            insertNonFull(x.getChildren().get(i), key);
        }
    }

    private void splitChild(BTreeNode x, int i) {
        BTreeNode y = x.getChildren().get(i);
        BTreeNode z = new BTreeNode(y.isLeaf());
        x.getKeys().add(i, y.getKeys().get(t - 1));
        x.getChildren().add(i + 1, z);
        z.getKeys().addAll(y.getKeys().subList(t, 2 * t - 1));
        y.getKeys().subList(t - 1, 2 * t - 1).clear();
        if (!y.isLeaf()) {
            z.getChildren().addAll(y.getChildren().subList(t, 2 * t));
            y.getChildren().subList(t, 2 * t).clear();
        }
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(BTreeNode x, int key) {
        int i = 0;
        while (i < x.getKeys().size() && key > x.getKeys().get(i)) {
            i++;
        }
        if (i < x.getKeys().size() && key == x.getKeys().get(i)) {
            return true;
        } else if (x.isLeaf()) {
            return false;
        } else {
            return search(x.getChildren().get(i), key);
        }
    }

    public void delete(int key) {
        delete(root, key);
    }

    private void delete(BTreeNode x, int key) {

    }


    public static void main(String[] args) {
        P23 bTree = new P23(3);

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
        bTree.insert(17);
        bTree.insert(12);
        bTree.insert(6);

        System.out.println("Search for key 6: " + bTree.search(6));
        System.out.println("Search for key 23: " + bTree.search(23));
        System.out.println("Search for key 16: " + bTree.search(16));
        System.out.println("Search for key 25: " + bTree.search(25));
    }
}