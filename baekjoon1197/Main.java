import java.io.*;
import java.util.*;

class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> edges = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        parent = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }

        int minWeight = 0;
        int edgeCount = 0;
        while (edgeCount < V - 1) {
            Edge curr = edges.poll();
            int rootA = find(curr.nodeA);
            int rootB = find(curr.nodeB);
            if (rootA != rootB) {
                parent[rootB] = rootA; // union
                minWeight += curr.weight;
                edgeCount++;
            }
        }
        
        System.out.println(minWeight);
    }

    static class Edge {
        int nodeA;
        int nodeB;
        int weight;
        Edge(int nodeA, int nodeB, int weight) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.weight = weight;
        }
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}