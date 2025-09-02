import java.io.*;
import java.util.*;

class Main {
    final static long MOD = 1_000_000_000;
    static int N;
    static long[][] dp; // dp[i][j]: 정수 i개를 더해서 합이 j가 되는 경우의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        dp = new long[K + 1][N + 1];
        System.out.println(dynamicProgramming(K, N));
    }

    private static long dynamicProgramming(int count, int targetSum) {
        if (count == 1) {
            if (targetSum <= N) {
                return 1;
            }
            return 0;
        }
        if (dp[count][targetSum] == 0) {
            for (int i = 0; i <= targetSum; i++) {
                dp[count][targetSum] += (dynamicProgramming(count - 1, targetSum - i) % MOD);
                dp[count][targetSum] %= MOD;
            }
        }
        return dp[count][targetSum];
    }
}