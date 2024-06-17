import java.util.*;
import java.io.*;

class Main {
    static int N;
        public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        List<Edge>[] graph = new List[N + 1];
        List<Edge>[] reversedGraph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reversedGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            graph[Integer.parseInt(input[0])].add(new Edge(Integer.parseInt(input[1]), Integer.parseInt(input[2])));
            reversedGraph[Integer.parseInt(input[1])].add(new Edge(Integer.parseInt(input[0]), Integer.parseInt(input[2])));
        }
        long[] fromX = dijkstra(X, graph);
        long[] toX = dijkstra(X, reversedGraph);
        long maxD = -1;
        for (int i = 1; i <= N; i++) {
            maxD = Math.max(maxD, fromX[i] + toX[i]);
        }
        System.out.println(maxD);
    }

    private static long[] dijkstra(int start, List<Edge>[] g) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> (int)(a.weight - b.weight));
        long[] distances = new long[N + 1];
        boolean[] isVisited = new boolean[N + 1];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[start] = 0;
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (isVisited[e.next])  continue;
            isVisited[e.next] = true;
            for (Edge neighbor : g[e.next]) {
                if (distances[neighbor.next] > distances[e.next] + neighbor.weight) {
                    distances[neighbor.next] = distances[e.next] + neighbor.weight;
                    pq.add(new Edge(neighbor.next, distances[neighbor.next]));        
                }
            }
        }
        return distances;
    }

    static class Edge {
        int next;
        long weight;
        Edge(int next, long weight) {
            this.next = next;
            this.weight = weight;
        }
    }
}