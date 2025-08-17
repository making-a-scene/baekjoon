import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] dp;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        if (N == 1) {
            System.out.println(0);
            return;
        }
        System.out.println(dynamicProgramming(0, N - 1));
    }

    private static int dynamicProgramming(int start, int end) {
        if (start + 1 == end) {
            return arr[start][0] * arr[start][1] * arr[end][1];
        }

        if (dp[start][end] == 0) {
            dp[start][end] = dynamicProgramming(start + 1, end) + arr[start][0] * arr[start][1] * arr[end][1];
            for (int i = start + 1; i < end - 1; i++) {
                dp[start][end] = Math.min(dp[start][end], dynamicProgramming(start, i) + dynamicProgramming(i + 1, end) + arr[start][0] * arr[i][1] * arr[end][1]);
            }
            dp[start][end] = Math.min(dp[start][end], dynamicProgramming(start, end - 1) + arr[start][0] * arr[end][0] * arr[end][1]);
        }
        return dp[start][end];
    }
}