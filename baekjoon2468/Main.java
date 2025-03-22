import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] region;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        region = new int[N][N];
        Set<Integer> values = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                region[i][j] = Integer.parseInt(input[j]);
                values.add(region[i][j]);
            }
        }

        int answer = 0;
        for (int amount : values) {
            answer = Math.max(answer, bfs(amount));
        }

        System.out.println(answer);
    }

    private static int bfs(int standard) {
        int count = 0;
        boolean[][] visited = new boolean[N][N];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && region[i][j] >= standard) {
                    visited[i][j] = true;
                    q.offer(new int[] {i, j});
                    while (!q.isEmpty()) {
                        int[] curr = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nextR = dx[k] + curr[0];
                            int nextC = dy[k] + curr[1];
                            if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < N
                                    && !visited[nextR][nextC] && region[nextR][nextC] >= standard) {
                                visited[nextR][nextC] = true;
                                q.offer(new int[] {nextR, nextC});
                            }
                        }
                    }
                    count++;
                }
            }
        }

        return count;
    }
}