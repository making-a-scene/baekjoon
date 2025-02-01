import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][M + 1];
        int[] A = new int[N + 1];
        int[] c = new int[N + 1];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], 1000000001);
            A[i] = Integer.parseInt(st1.nextToken());
            c[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 1; i <= A[1]; i++) {
            dp[1][i] = c[1];
        }

        for (int range = 2; range <= N; range++) {
            for (int needed = 1; needed <= M; needed++) {
                if (needed <= A[range]) {
                    dp[range][needed] = Math.min(dp[range - 1][needed], c[range]); 
                } else {
                    dp[range][needed] = Math.min(dp[range - 1][needed], c[range] + dp[range - 1][needed - A[range]]); 
                }
            }
        }
    }
}