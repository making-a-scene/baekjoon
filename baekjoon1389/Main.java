import java.io.*;
import java.util.*;

class Main {
    static List<Integer>[] relation;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][N + 1];
        relation = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            relation[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relation[a].add(b);
            relation[b].add(a);
        }

        int minVal = Integer.MAX_VALUE;
        int minNum = 0;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (dp[j][i] == 0) {
                    dp[j][i] = bfs(i, j);
                }
                dp[i][j] = dp[j][i];
                sum += dp[i][j];
            }
            if (sum < minVal) {
                minVal = sum;
                minNum = i;
            }
        }
        System.out.println(minNum);
    }

    private static int bfs(int start, int end) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] isVisited = new boolean[N + 1];
        q.offer(new int[] {start, 0});
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0] == end) return curr[1];
            for (int next : relation[curr[0]]) {
                if (!isVisited[next]) {
                    isVisited[next] = true;
                    q.offer(new int[] {next, curr[1] + 1});
                }
            }
        }
        return -1;
    }
}