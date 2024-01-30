import java.util.ArrayList;
import java.util.PriorityQueue;

public class P30 {

    static class Edge{
        int src;
        int dest;
        int wet;
        Edge(int src,int dest,int wet){
            this.src=src;
            this.dest=dest;
            this.wet=wet;
        }
    }
    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }


        graph[0].add(new Edge(0,1,2));
        graph[0].add(new Edge(0,2,4));

        graph[1].add(new Edge(1,2,-4));

        graph[2].add(new Edge(2,3,2));

        graph[3].add(new Edge(3,4,4));

        graph[4].add(new Edge(4,1,-1));
    }

    public static class Pair implements Comparable<Pair>{  //O(E+E logV)
        int node;
        int dest;
        Pair(int node,int dest){
            this.dest=dest;
            this.node=node;
        }


        @Override
        public int compareTo(Pair p2) {
            return this.dest-p2.dest;
        }
    }



    public static int[] bellmanFord(ArrayList<Edge> graph[],int src,int vertax){
        int distance[]=new int[vertax];
        for(int i=0;i<distance.length;i++){
            if(i != src){
                distance[i]=Integer.MAX_VALUE;
            }
        }

        for(int k=0;k<vertax-1;k++){
            for(int j=0;j<vertax;j++){
                for(int i=0;i<graph[j].size();i++){
                    Edge e=graph[j].get(i);
                    int u=e.src;
                    int v=e.dest;
                    if(distance[u] != Integer.MAX_VALUE && distance[v]>distance[u]+e.wet){
                        distance[v]=distance[u]+e.wet;
                    }
                }
            }
        }

        //another time loop execute so we decide in a graph negative life cycle

        for(int j=0;j<vertax;j++){
            for(int i=0;i<graph[j].size();i++){
                Edge e=graph[j].get(i);
                int u=e.src;
                int v=e.dest;
                if(distance[u] != Integer.MAX_VALUE && distance[v]>distance[u]+e.wet){
                    System.out.println("Negative cycle detect!!");
                }
            }
        }


        return  distance;
    }
    public static void main(String args[]){

        int vertax=5;
        ArrayList<Edge> graph[]=new ArrayList[vertax];

        createGraph(graph);



        int distance[]=bellmanFord(graph,0,vertax);

        for(int i=0;i<vertax;i++){
            System.out.print(distance[i]+" ");
        }
        System.out.println();
    }
}
