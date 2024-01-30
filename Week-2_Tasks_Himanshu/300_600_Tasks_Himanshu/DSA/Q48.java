import java.util.Arrays;

class Suffix implements Comparable<Suffix> {
    int index;
    int rank;
    int nextRank;

    public Suffix(int index, int rank, int nextRank) {
        this.index = index;
        this.rank = rank;
        this.nextRank = nextRank;
    }

    @Override
    public int compareTo(Suffix suffix) {
        if (this.rank != suffix.rank) {
            return Integer.compare(this.rank, suffix.rank);
        }
        return Integer.compare(this.nextRank, suffix.nextRank);
    }
}

public class Q48 {

    public static int[] buildSuffixArray(String text) {
        int n = text.length();
        Suffix[] suffixes = new Suffix[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(i, text.charAt(i) - 'a', i + 1 < n ? text.charAt(i + 1) - 'a' : -1);
        }

        Arrays.sort(suffixes);

        int[] index = new int[n];
        for (int k = 4; k < 2 * n; k <<= 1) {
            int rank = 0;
            int prevRank = suffixes[0].rank;
            suffixes[0].rank = rank;
            index[suffixes[0].index] = 0;

            for (int i = 1; i < n; i++) {
                if (suffixes[i].rank == prevRank && suffixes[i].nextRank == suffixes[i - 1].nextRank) {
                    prevRank = suffixes[i].rank;
                    suffixes[i].rank = rank;
                } else {
                    prevRank = suffixes[i].rank;
                    suffixes[i].rank = ++rank;
                }
                index[suffixes[i].index] = i;
            }

            for (int i = 0; i < n; i++) {
                int nextIndex = suffixes[i].index + k / 2;
                suffixes[i].nextRank = nextIndex < n ? suffixes[index[nextIndex]].rank : -1;
            }

            Arrays.sort(suffixes);
        }

        int[] suffixArray = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i].index;
        }

        return suffixArray;
    }

    public static void main(String[] args) {
        String text = "banana";
        int[] suffixArray = buildSuffixArray(text);

        System.out.println("Suffix Array for '" + text + "': " + Arrays.toString(suffixArray));
    }
}
