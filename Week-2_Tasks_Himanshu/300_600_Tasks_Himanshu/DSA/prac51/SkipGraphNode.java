package prac51;

import java.util.ArrayList;
import java.util.List;

public class SkipGraphNode {
	int key;
	List<SkipGraphNode> next;

	public SkipGraphNode(int key, int level) {
		this.key = key;
		this.next = new ArrayList<>(level + 1);
		for (int i = 0; i <= level; i++) {
			this.next.add(null);
		}
	}
}