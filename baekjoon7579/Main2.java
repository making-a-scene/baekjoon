import java.io.*;
import java.util.*;

class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // dp[range][cost]: A_1부터 A_range까지의 앱 중에서 cost만큼의 비용을 소모해 얻을 수 있는 최대 메모리
        int[][] dp = new int[N + 1][10001]; 
        int[] A = new int[N + 1];
        int[] c = new int[N + 1];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st1.nextToken());
            c[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = c[1]; i <= 10000; i++) {
            dp[1][i] = A[1];
        }

        for (int range = 2; range < N; range++) {
            for (int cost = 0; cost < c[range]; cost++) {
                dp[range][cost] = dp[range - 1][cost];
            } 
            for (int cost = c[range]; cost <= 10000; cost++) {
                dp[range][cost] = Math.max(dp[range - 1][cost], A[range] + dp[range - 1][cost - c[range]]);
            }
        }
        for (int cost = 0; cost < c[N]; cost++) {
            if (dp[N - 1][cost] >= M) {
                System.out.println(cost);
                return;
            }
        }
        for (int cost = c[N]; cost <= 10000; cost++) {
            if (Math.max(dp[N - 1][cost], A[N] + dp[N - 1][cost - c[N]]) >= M) {
                System.out.println(cost);
                return;
            }
        }
    }
}