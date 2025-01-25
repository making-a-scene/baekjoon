import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int[][] apps;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        apps = new int[N][2];
        dp = new int[N][M + 1]; // dp[i][j]: apps[0]부터 apps[i]까지의 앱들로 j 바이트를 확보하기 위한 최소 비용

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            apps[i][0] = Integer.parseInt(st1.nextToken());
            apps[i][1] = Integer.parseInt(st2.nextToken());
            Arrays.fill(dp[i], 10000001);
        }
        for (int j = 0; j <= apps[0][0]; j++) {
            dp[0][j] = apps[0][1];
        }
        
        for (int needed = 1; needed <= M; needed++) {
            for (int limit = 1; limit < N; limit++) {
                dynamicProgramming(limit, needed);
            }
        }

        System.out.println(dp[N - 1][M]);
    }

    private static int dynamicProgramming(int limit, int needed) {
        if (limit != 0 && dp[limit][needed] == 10000001) {
            for (int i = limit - 1; i >= 0; i--) {
                if (needed - apps[limit][0] > 0) {
                  dp[limit][needed] = Math.min(dp[limit][needed], apps[limit][1] + dynamicProgramming(i, needed - apps[limit][0]));
                } else {
                  dp[limit][needed] = Math.min(dp[limit][needed], apps[i][1]);
                }
            }     
            dp[limit][needed] = Math.min(dp[limit][needed], dp[limit - 1][needed]);
        }
        return dp[limit][needed];
    }
}