package prac61;

class Node {
	Node parent, left, right;
	boolean isRoot, isReversed;
	int value;

	Node(int value) {
		this.value = value;
		parent = left = right = null;
		isRoot = true;
		isReversed = false;
	}
}

public class LinkCutTree {
	private static void makeRoot(Node x) {
		access(x);
		x.isReversed = !x.isReversed;
	}

	private static void access(Node x) {
		Node last = null;
		for (Node y = x; y != null; y = y.parent) {
			splay(y);
			y.right = last;
			last = y;
		}
		splay(x);
	}

	private static void splay(Node x) {
		while (!x.isRoot) {
			Node p = x.parent;
			Node g = p.parent;

			if (!p.isRoot) {
				push(p.parent);
			}
			push(p);
			push(x);

			if (!p.isRoot) {
				if ((g.left == p) == (p.left == x)) {
					rotate(p);
				} else {
					rotate(x);
				}
			}

			rotate(x);
		}
	}

	private static void rotate(Node x) {
		Node p = x.parent;
		Node g = p.parent;

		if (!p.isRoot) {
			if (g.left == p) {
				if (p.left == x) {
					setChild(g, p.right, true);
					setChild(p, x.right, false);
					setChild(x, p, true);
					setChild(p, g, false);
				} else {
					setChild(g, x.right, true);
					setChild(x, p.right, false);
					setChild(p, x.left, true);
					setChild(x, g, false);
				}
			} else {
				if (p.right == x) {
					setChild(g, p.left, false);
					setChild(p, x.left, true);
					setChild(x, p, false);
					setChild(p, g, true);
				} else {
					setChild(g, x.left, false);
					setChild(x, p.left, true);
					setChild(p, x.right, false);
					setChild(x, g, true);
				}
			}
		} else {
			if (p.left == x) {
				setChild(p, x.right, false);
				setChild(x, p, true);
			} else {
				setChild(p, x.left, true);
				setChild(x, p, false);
			}
		}
	}

	private static void setChild(Node parent, Node child, boolean isLeft) {
		if (child != null) {
			child.parent = parent;
		}
		if (isLeft) {
			parent.left = child;
		} else {
			parent.right = child;
		}
	}

	private static void push(Node x) {
		if (x.isReversed) {
			Node temp = x.left;
			x.left = x.right;
			x.right = temp;
			x.isReversed = false;

			if (x.left != null) {
				x.left.isReversed = !x.left.isReversed;
			}
			if (x.right != null) {
				x.right.isReversed = !x.right.isReversed;
			}
		}
	}

	private static void link(Node x, Node y) {
		makeRoot(x);
		x.parent = y;
	}

	private static void cut(Node x) {

		access(x);
		x.left.parent = null;
		x.left = null;
	}

	private static Node findRoot(Node x) {
		access(x);
		while (x.right != null) {
			x = x.right;
		}
		splay(x);
		return x;
	}

	private static boolean connected(Node x, Node y) {
		return findRoot(x) == findRoot(y);
	}

	public static void main(String[] args) {
		Node[] nodes = new Node[5];
		for (int i = 0; i < 5; i++) {
			nodes[i] = new Node(i + 1);
		}

		link(nodes[1], nodes[2]);
		link(nodes[2], nodes[3]);
		link(nodes[4], nodes[3]);

		System.out.println(connected(nodes[1], nodes[4]));
		System.out.println(connected(nodes[1], nodes[3]));

		cut(nodes[2]);

		System.out.println(connected(nodes[1], nodes[3]));
	}
}