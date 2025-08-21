import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int count = 1;
        while (true) { 
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            int[][] cave = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            sb.append(String.format("Problem %d: %d\n", count++, dijkstra(N, cave)));
        }
        System.out.print(sb.toString());
    }

    private static int dijkstra(int N, int[][] cave) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] distances = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        
        for (int i = 0; i < N; i++) {
            Arrays.fill(distances[i], N * N * 9);
        }
        pq.offer(new Edge(cave[0][0], 0, 0));
        distances[0][0] = cave[0][0];

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (visited[curr.row][curr.col]) {
                continue;
            }
            visited[curr.row][curr.col] = true;
            
            for (int i = 0; i < 4; i++) {
                int neighborRow = curr.row + dx[i];
                int neighborCol = curr.col + dy[i];
                if (neighborRow < 0 || neighborRow >= N || neighborCol < 0 || neighborCol >= N) {
                    continue;
                }
                if (distances[neighborRow][neighborCol] > distances[curr.row][curr.col] + cave[neighborRow][neighborCol]) {
                    distances[neighborRow][neighborCol] = distances[curr.row][curr.col] + cave[neighborRow][neighborCol];
                    pq.offer(new Edge(distances[neighborRow][neighborCol], neighborRow, neighborCol));
                }
            }
        }

        return distances[N - 1][N - 1];
    }

    static class Edge {
        int cost;
        int row;
        int col;
        public Edge(int cost, int row, int col) {
            this.cost = cost;
            this.row = row;
            this.col = col;
        }
    }
}