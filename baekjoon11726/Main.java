import java.io.*;

class Main {
    static int[] dp;
    final static int MOD = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        System.out.println(tiling(n));
    }

    private static int tiling(int k) {
        if (k == 1) return 1;
        if (k == 2) return 2;
        if (dp[k] == 0) {
            dp[k] = (tiling(k - 1) + tiling(k - 2)) % MOD;
        }
        return dp[k];
    }
}