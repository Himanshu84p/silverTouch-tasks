package prac75;

class SplayTree {
    class Node {
        int key;
        Node left, right, parent;

        Node(int key) {
            this.key = key;
            this.left = this.right = this.parent = null;
        }
    }

    private Node root;

    public SplayTree() {
        this.root = null;
    }

    private void splay(Node x) {
        while (x.parent != null) {
            Node parent = x.parent;
            Node grandparent = parent.parent;

            if (grandparent == null) {

                if (x == parent.left) {
                    rotateRight(parent);
                } else {
                    rotateLeft(parent);
                }
            } else {

                if (x == parent.left && parent == grandparent.left) {
                    rotateRight(grandparent);
                    rotateRight(parent);
                } else if (x == parent.right && parent == grandparent.right) {
                    rotateLeft(grandparent);
                    rotateLeft(parent);
                } else if (x == parent.right && parent == grandparent.left) {
                    rotateLeft(parent);
                    rotateRight(grandparent);
                } else {
                    rotateRight(parent);
                    rotateLeft(grandparent);
                }
            }
        }
    }

    private void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void insert(int key) {
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (key < current.key) {
                current = current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {

                splay(current);
                return;
            }
        }

        newNode.parent = parent;
        if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        splay(newNode);
    }

    public void delete(int key) {
        Node node = search(key);
        if (node != null) {
            splay(node);

            if (node.left == null) {
                root = node.right;
                if (root != null) {
                    root.parent = null;
                }
            } else {
                Node maxLeft = findMax(node.left);
                splay(maxLeft);
                maxLeft.right = node.right;
                if (node.right != null) {
                    node.right.parent = maxLeft;
                }
                root = maxLeft;
            }
        }
    }

    public Node search(int key) {
        Node current = root;
        while (current != null) {
            if (key < current.key) {
                current = current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {
                splay(current);
                return current;
            }
        }
        return null;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.key + " ");
            inOrderTraversal(node.right);
        }
    }

    public void printInOrder() {
        System.out.print("In-Order Traversal: ");
        inOrderTraversal(root);
        System.out.println();
    }

    public static void main(String[] args) {
        SplayTree splayTree = new SplayTree();

        splayTree.insert(50);
        splayTree.insert(30);
        splayTree.insert(70);
        splayTree.insert(20);
        splayTree.insert(40);
        splayTree.insert(60);
        splayTree.insert(80);

        splayTree.printInOrder();

        splayTree.search(40);
        splayTree.printInOrder();

        splayTree.delete(30);
        splayTree.printInOrder();
    }
}
