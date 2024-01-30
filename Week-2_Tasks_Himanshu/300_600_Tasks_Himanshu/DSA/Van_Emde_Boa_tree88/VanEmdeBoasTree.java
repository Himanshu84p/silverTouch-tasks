package Van_Emde_Boa_tree88;

import java.util.Arrays;

public class VanEmdeBoasTree {
    private int universeSize;
    private int min, max;
    private VanEmdeBoasTree summary;
    private VanEmdeBoasTree[] clusters;

    public VanEmdeBoasTree(int universeSize) {
        this.universeSize = universeSize;

        if (universeSize > 2) {
            int highBits = high(universeSize);
            int clusterSize = (int) Math.ceil(Math.sqrt(highBits));
            int clusterUniverseSize = (int) Math.pow(2, clusterSize);

            this.min = -1;
            this.max = -1;
            this.summary = new VanEmdeBoasTree(clusterUniverseSize);
            this.clusters = new VanEmdeBoasTree[clusterUniverseSize];

            for (int i = 0; i < clusterUniverseSize; i++) {
                this.clusters[i] = new VanEmdeBoasTree((int) Math.pow(2, clusterSize));
            }
        } else {
            this.min = -1;
            this.max = -1;
        }
    }

    private int high(int x) {
        return (int) Math.floor(x / 2);
    }

    private int low(int x) {
        return x % 2 == 0 ? x / 2 : (x / 2) + 1;
    }

    private int index(int x, int y) {
        return x * high(universeSize) + y;
    }

    public boolean contains(int key) {
        if (universeSize == 2) {
            return key == min || key == max;
        } else {
            return clusters[high(key)].contains(low(key));
        }
    }

    public int successor(int key) {
        if (universeSize == 2) {
            if (key == 0 && max == 1) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (min != -1 && key < min) {
                return min;
            } else {
                int maxLow = clusters[high(key)].max;
                if (maxLow != -1 && low(key) < maxLow) {
                    int offset = clusters[high(key)].successor(low(key));
                    return index(high(key), offset);
                } else {
                    int succCluster = summary.successor(high(key));
                    if (succCluster == -1) {
                        return -1;
                    } else {
                        int offset = clusters[succCluster].min;
                        return index(succCluster, offset);
                    }
                }
            }
        }
    }

    public void insert(int key) {
        if (universeSize == 2) {
            if (min == -1) {
                min = max = key;
            } else {
                min = Math.min(min, key);
                max = Math.max(max, key);
            }
        } else {
            if (min == -1) {
                min = max = key;
            } else {
                min = Math.min(min, key);
                max = Math.max(max, key);
            }

            clusters[high(key)].insert(low(key));
            summary.insert(high(key));
        }
    }

    public void delete(int key) {
        if (universeSize == 2) {
            if (key == min && key == max) {
                min = max = -1;
            }
        } else {
            if (key == min) {
                int firstCluster = summary.min;
                key = index(firstCluster, clusters[firstCluster].min);
                min = key;
            }
            clusters[high(key)].delete(low(key));

            if (clusters[high(key)].min == -1) {
                summary.delete(high(key));
                if (key == max) {
                    int summaryMax = summary.max;
                    if (summaryMax == -1) {
                        max = min;
                    } else {
                        max = index(summaryMax, clusters[summaryMax].max);
                    }
                }
            } else if (key == max) {
                max = index(high(key), clusters[high(key)].max);
            }
        }
    }

    public static void main(String[] args) {
        VanEmdeBoasTree vebTree = new VanEmdeBoasTree(16);

        vebTree.insert(2);
        vebTree.insert(3);
        vebTree.insert(4);
        vebTree.insert(6);

        System.out.println("Contains 4: " + vebTree.contains(4));
        System.out.println("Successor of 3: " + vebTree.successor(3));

        vebTree.delete(4);

        System.out.println("Contains 4: " + vebTree.contains(4));
    }
}
