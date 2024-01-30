package prac52;

public class BTreeNode {
	int[] keys;
	BTreeNode[] children;
	int numKeys;
	boolean leaf;

	public BTreeNode(int t, boolean leaf) {
		this.keys = new int[2 * t - 1];
		this.children = new BTreeNode[2 * t];
		this.numKeys = 0;
		this.leaf = leaf;
	}
}
