package LinkCut89;

import java.util.HashMap;
import java.util.Map;



class Node {
    int key;
    int value;
    int sum;
    Node parent, left, right;
    boolean isRoot;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.sum = value;
        this.parent = this.left = this.right = null;
        this.isRoot = true;
    }
}

public class LinkCutTree {
    private Map<Integer, Node> nodes;

    public LinkCutTree() {
        this.nodes = new HashMap<>();
    }

    public void makeNode(int key, int value) {
        if (!nodes.containsKey(key)) {
            Node node = new Node(key, value);
            nodes.put(key, node);
        }
    }

    private void link(Node parent, Node child) {
        child.parent = parent;
        if (parent.left == null) {
            parent.left = child;
        } else {
            Node rightmost = findRightmost(parent.left);
            rightmost.right = child;
        }
        updateSum(parent);
    }

    private void cut(Node child) {
        if (child.parent != null) {
            Node parent = child.parent;
            child.parent = null;
            if (child == parent.left) {
                parent.left = null;
            } else {
                Node current = parent.left;
                while (current.right != child) {
                    current = current.right;
                }
                current.right = null;
            }
            updateSum(parent);
        }
    }

    private void zig(Node x) {
        Node y = x.parent;
        Node z = y.parent;

        x.parent = z;
        y.parent = x;
        y.left = x.right;
        x.right = y;

        if (y.left != null) {
            y.left.parent = y;
        }

        if (z != null) {
            if (y == z.left) {
                z.left = x;
            } else {
                z.right = x;
            }
        }

        updateSum(y);
        updateSum(x);
    }

    private void zigZig(Node x) {
        Node y = x.parent;
        Node z = y.parent;
        Node grandparent = z.parent;

        x.parent = grandparent;
        y.parent = x;
        z.parent = y;

        y.left = x.right;
        z.left = y.right;

        x.right = y;
        y.right = z;

        if (y.left != null) {
            y.left.parent = y;
        }

        if (z.left != null) {
            z.left.parent = z;
        }

        if (grandparent != null) {
            if (z == grandparent.left) {
                grandparent.left = x;
            } else {
                grandparent.right = x;
            }
        }

        updateSum(z);
        updateSum(y);
        updateSum(x);
    }

    private void zigZag(Node x) {
        Node y = x.parent;
        Node z = y.parent;
        Node grandparent = z.parent;

        x.parent = grandparent;
        y.parent = x;
        z.parent = x;

        y.right = x.left;
        z.left = x.right;

        x.left = y;
        x.right = z;

        if (y.right != null) {
            y.right.parent = y;
        }

        if (z.left != null) {
            z.left.parent = z;
        }

        if (grandparent != null) {
            if (z == grandparent.left) {
                grandparent.left = x;
            } else {
                grandparent.right = x;
            }
        }

        updateSum(z);
        updateSum(y);
        updateSum(x);
    }

    private void splay(Node x) {
        while (!x.isRoot) {
            Node y = x.parent;
            Node z = y.parent;

            if (y.isRoot) {
                if (x == y.left) {
                    zig(x);
                } else {
                    zag(x);
                }
            } else {
                if (x == y.left) {
                    if (y == z.left) {
                        zigZig(x);
                    } else {
                        zigZag(x);
                    }
                } else {
                    if (y == z.left) {
                        zagZig(x);
                    } else {
                        zagZag(x);
                    }
                }
            }
        }
    }

    private void zag(Node x) {
        Node y = x.parent;
        Node z = y.parent;

        x.parent = z;
        y.parent = x;
        y.right = x.left;
        x.left = y;

        if (y.right != null) {
            y.right.parent = y;
        }

        if (z != null) {
            if (y == z.left) {
                z.left = x;
            } else {
                z.right = x;
            }
        }

        updateSum(y);
        updateSum(x);
    }

    private void zagZag(Node x) {
        Node y = x.parent;
        Node z = y.parent;
        Node grandparent = z.parent;

        x.parent = grandparent;
        y.parent = x;
        z.parent = y;

        y.right = x.left;
        z.right = y.left;

        x.left = y;
        y.left = z;

        if (y.right != null) {
            y.right.parent = y;
        }

        if (z.right != null) {
            z.right.parent = z;
        }

        if (grandparent != null) {
            if (z == grandparent.left) {
                grandparent.left = x;
            } else {
                grandparent.right = x;
            }
        }

        updateSum(z);
        updateSum(y);
        updateSum(x);
    }

    private void zagZig(Node x) {
        Node y = x.parent;
        Node z = y.parent;
        Node grandparent = z.parent;

        x.parent = grandparent;
        y.parent = x;
        z.parent = x;

        y.left = x.right;
        z.right = x.left;

        x.left = z;
        x.right = y;

        if (y.left != null) {
            y.left.parent = y;
        }

        if (z.right != null) {
            z.right.parent = z;
        }

        if (grandparent != null) {
            if (z == grandparent.left) {
                grandparent.left = x;
            } else {
                grandparent.right = x;
            }
        }

        updateSum(z);
        updateSum(y);
        updateSum(x);
    }

    public void access(int key) {
        Node x = nodes.get(key);
        splay(x);

        if (x.right != null) {
            x.right.isRoot = true;
            x.right.parent = null;
            x.right = null;
            updateSum(x);
        }
    }

    public void link(int parentKey, int childKey) {
        access(parentKey);
        access(childKey);

        Node parent = nodes.get(parentKey);
        Node child = nodes.get(childKey);

        if (parent.right == null) {
            parent.right = child;
            child.parent = parent;
            child.isRoot = true;
            updateSum(parent);
        }
    }

    public void cut(int key) {
        access(key);
        Node x = nodes.get(key);
        Node parent = x.left;
        x.left = null;
        updateSum(parent);

        if (parent != null) {
            parent.parent = null;
            parent.isRoot = true;
        }
    }

    public int queryPathSum(int key) {
        access(key);
        return nodes.get(key).sum;
    }

    public void updateValue(int key, int newValue) {
        access(key);
        Node x = nodes.get(key);
        x.value = newValue;
        updateSum(x);
    }

    private Node findRightmost(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private void updateSum(Node x) {
        x.sum = x.value;

        if (x.left != null) {
            x.sum += x.left.sum;
        }
        if (x.right != null) {
            x.sum += x.right.sum;
        }
    }

    public static void main(String[] args) {
        LinkCutTree lct = new LinkCutTree();

        lct.makeNode(1, 3);
        lct.makeNode(2, 7);
        lct.makeNode(3, 5);

        lct.link(1, 2);
        lct.link(2, 3);

        System.out.println("Path sum from root to node 3: " + lct.queryPathSum(3));

        lct.updateValue(2, 10);

        System.out.println("Path sum from root to node 3 after update: " + lct.queryPathSum(3));
    }
}
