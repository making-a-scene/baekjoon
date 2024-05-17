import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        List<Edge>[] graph = new List[V + 1];
        int[] shortest = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        
        Arrays.fill(shortest, Integer.MAX_VALUE);
        shortest[K] = 0;
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);
            graph[u].add(new Edge(v, w));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(K, 0));
        while (!pq.isEmpty()) {
            int currNode = pq.poll().to;
            if (visited[currNode])  continue;
            visited[currNode] = true;
            List<Edge> neighbors = graph[currNode];
            if (neighbors.isEmpty())    continue;
            for (Edge neighbor : neighbors) {
                if (visited[neighbor.to])  continue;
                if (shortest[currNode] + neighbor.weight <= shortest[neighbor.to]) {
                    shortest[neighbor.to] = shortest[currNode] + neighbor.weight;
                    pq.offer(new Edge(neighbor.to, shortest[neighbor.to]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (shortest[i] == Integer.MAX_VALUE)   System.out.println("INF");
            else    System.out.println(shortest[i]);
        }   
    }

    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}