import java.io.*;

class Main {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        dp = new int[41][2];
        dp[0][0] = dp[1][1] = 1;
        dp[0][1] = dp[1][0] = 0;
        
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            fibonacci(N);
            sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int[] fibonacci(int n) {
        if (dp[n][0] != 0 && dp[n][1] != 0) return dp[n];

        if (n == 0) {
            return dp[0];
        } else if (n == 1) {
            return dp[1];
        }
        int[] a = fibonacci(n - 1);
        int[] b = fibonacci(n - 2);
        dp[n][0] = a[0] + b[0];
        dp[n][1] = a[1] + b[1];
        return dp[n];
    }
}