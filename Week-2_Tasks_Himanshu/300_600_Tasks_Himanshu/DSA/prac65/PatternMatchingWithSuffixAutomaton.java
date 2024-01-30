package prac65;

import java.util.*;

class SuffixAutomaton {
    static class State {
        int length;
        int link;
        Map<Character, Integer> transitions;

        State(int length, int link) {
            this.length = length;
            this.link = link;
            this.transitions = new HashMap<>();
        }
    }

    private List<State> states;
    private int last;

    public SuffixAutomaton(String text) {
        states = new ArrayList<>();
        last = addState(0, -1);

        for (char c : text.toCharArray()) {
            addCharacter(c);
        }

        int p = last;
        while (p != -1) {
            states.get(p).length = Integer.MAX_VALUE;
            p = states.get(p).link;
        }
    }

    private int addState(int length, int link) {
        State state = new State(length, link);
        states.add(state);
        return states.size() - 1;
    }

    private void addCharacter(char c) {
        int cur = addState(states.get(last).length + 1, 0);
        int p = last;

        while (p != -1 && !states.get(p).transitions.containsKey(c)) {
            states.get(p).transitions.put(c, cur);
            p = states.get(p).link;
        }

        if (p == -1) {
            states.get(cur).link = 0;
        } else {
            int q = states.get(p).transitions.get(c);

            if (states.get(p).length + 1 == states.get(q).length) {
                states.get(cur).link = q;
            } else {
                int clone = addState(states.get(p).length + 1, states.get(q).link);
                states.get(clone).transitions = new HashMap<>(states.get(q).transitions);

                while (p != -1 && states.get(p).transitions.get(c) == q) {
                    states.get(p).transitions.put(c, clone);
                    p = states.get(p).link;
                }

                states.get(q).link = clone;
                states.get(cur).link = clone;
            }
        }

        last = cur;
    }

    public List<Integer> findOccurrences(String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        int state = 0;

        for (char c : pattern.toCharArray()) {
            if (!states.get(state).transitions.containsKey(c)) {
                return occurrences;
            }
            state = states.get(state).transitions.get(c);
        }

        dfs(state, occurrences);

        return occurrences;
    }

    private void dfs(int state, List<Integer> occurrences) {
        if (states.get(state).length != Integer.MAX_VALUE) {
            occurrences.add(states.get(state).length);
        }

        for (int next : states.get(state).transitions.values()) {
            dfs(next, occurrences);
        }
    }

}

public class PatternMatchingWithSuffixAutomaton {
    public static void main(String[] args) {
        String text = "ababc";
        SuffixAutomaton automaton = new SuffixAutomaton(text);

        String pattern = "ab";
        List<Integer> occurrences = automaton.findOccurrences(pattern);

        if (!occurrences.isEmpty()) {
            System.out.println("Pattern \"" + pattern + "\" found at positions: " + occurrences);
        } else {
            System.out.println("Pattern \"" + pattern + "\" not found in the text.");
        }
    }
}
