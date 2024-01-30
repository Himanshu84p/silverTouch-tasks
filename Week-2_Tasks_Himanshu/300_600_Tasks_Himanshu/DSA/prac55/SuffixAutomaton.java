package prac55;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuffixAutomaton {
	static final int ALPHABET_SIZE = 26;

	class State {
		int length, link;
		Map<Character, Integer> transitions;

		public State(int length) {
			this.length = length;
			this.link = -1;
			this.transitions = new HashMap<>();
		}
	}

	List<State> states;
	int last;

	public SuffixAutomaton(String s) {
		states = new ArrayList<>();
		states.add(new State(0));
		last = 0;

		for (char c : s.toCharArray()) {
			extend(c);
		}

		int cur = last;
		while (cur != -1) {
			states.get(cur).length = Integer.MAX_VALUE;
			cur = states.get(cur).link;
		}
		states.get(0).length = 0;
	}

	private void extend(char c) {
		int newLast = states.size();
		states.add(new State(states.get(last).length + 1));
		int cur = last;

		while (cur != -1 && !states.get(cur).transitions.containsKey(c)) {
			states.get(cur).transitions.put(c, newLast);
			cur = states.get(cur).link;
		}

		if (cur == -1) {
			states.get(newLast).link = 0;
		} else {
			int next = states.get(cur).transitions.get(c);
			if (states.get(cur).length + 1 == states.get(next).length) {
				states.get(newLast).link = next;
			} else {
				int clone = states.size();
				states.add(new State(states.get(cur).length + 1));
				states.get(clone).transitions.putAll(states.get(next).transitions);
				states.get(clone).link = states.get(next).link;

				while (cur != -1 && states.get(cur).transitions.get(c) == next) {
					states.get(cur).transitions.put(c, clone);
					cur = states.get(cur).link;
				}

				states.get(next).link = states.get(newLast).link = clone;
			}
		}

		last = newLast;
	}

	public static void main(String[] args) {
		String text = "abab";
		SuffixAutomaton sa = new SuffixAutomaton(text);

		System.out.println("States:");
		for (int i = 0; i < sa.states.size(); i++) {
			System.out.println("State " + i + ": " + sa.states.get(i).transitions);
		}
	}
}
