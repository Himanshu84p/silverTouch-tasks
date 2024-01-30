package prac53;

public class DancingNode {
	DancingNode left, right, up, down;
	ColumnNode column;

	DancingNode() {
		left = right = up = down = this;
	}

	DancingNode(ColumnNode column) {
		this();
		this.column = column;
	}

	void unlinkLR() {
		left.right = right;
		right.left = left;
	}

	void unlinkUD() {
		up.down = down;
		down.up = up;
	}

	void relinkLR() {
		left.right = this;
		right.left = this;
	}

	void relinkUD() {
		up.down = this;
		down.up = this;
	}

	void linkDown(DancingNode node) {
		node.down = down;
		node.up = this;
		down.up = node;
		down = node;
	}

	void linkRight(DancingNode node) {
		node.right = right;
		node.left = this;
		right.left = node;
		right = node;
	}
}

class ColumnNode extends DancingNode {
	int size;
	String name;

	ColumnNode(String name) {
		super();
		this.size = 0;
		this.name = name;
		this.column = this;
	}

	void cover() {
		unlinkLR();

		for (DancingNode i = this.down; i != this; i = i.down) {
			for (DancingNode j = i.right; j != i; j = j.right) {
				j.unlinkUD();
				j.column.size--;
			}
		}
	}

	void uncover() {
		for (DancingNode i = this.up; i != this; i = i.up) {
			for (DancingNode j = i.left; j != i; j = j.left) {
				j.column.size++;
				j.relinkUD();
			}
		}

		relinkLR();
	}
}