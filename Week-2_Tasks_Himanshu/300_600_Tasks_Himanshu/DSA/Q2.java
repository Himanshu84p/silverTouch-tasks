import java.util.Arrays;
import java.util.Comparator;

class Job {
    char id;
    int deadline, profit;

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

class DisjointSet {
    int[] parent;

    DisjointSet(int n) {
        parent = new int[n + 1];
        Arrays.fill(parent, -1);
    }

    int find(int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent[i]);
    }

    void union(int i, int j) {
        int parentI = find(i);
        int parentJ = find(j);
        if (parentI != parentJ) {
            parent[parentI] = parentJ;
        }
    }
}

class JobSequencing {

    static void jobSequence(Job[] jobs) {

        Arrays.sort(jobs, Comparator.comparingInt(job -> -job.profit));

        int maxDeadline = Arrays.stream(jobs).mapToInt(job -> job.deadline).max().orElse(0);
        DisjointSet ds = new DisjointSet(maxDeadline);

        char[] result = new char[maxDeadline];
        Arrays.fill(result, ' ');

        for (Job job : jobs) {

            int slot = ds.find(job.deadline);

            if (slot > 0) {
                result[slot - 1] = job.id;
                ds.union(ds.find(slot - 1), ds.find(slot));
            }
        }

        System.out.println("Job sequence for maximum profit:");
        for (char ch : result) {
            if (ch != ' ') {
                System.out.print(ch + " ");
            }
        }
    }

    public static void main(String[] args) {
        Job[] jobs = {
                new Job('a', 2, 100),
                new Job('b', 1, 19),
                new Job('c', 2, 27),
                new Job('d', 1, 25),
                new Job('e', 3, 15)
        };

        jobSequence(jobs);
    }
}
