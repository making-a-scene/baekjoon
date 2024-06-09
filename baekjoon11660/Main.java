import java.util.*;
import java.io.*;

class Main {
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        dp = new long[N + 1][N + 1];
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            long sum = 0;
            for (int j = 1; j <= N; j++) {
                sum += Integer.parseInt(input[j - 1]);
                dp[i][j] = sum;
            }
        }

        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            int x1 = Integer.parseInt(input[0]);
            int y1 = Integer.parseInt(input[1]);
            int x2 = Integer.parseInt(input[2]);
            int y2 = Integer.parseInt(input[3]);
            sb.append(getPartSum(x1, y1, x2, y2)).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static long getPartSum(int x1, int y1, int x2, int y2) {
        long sum = 0;
        for (int x = x1; x <= x2; x++) {
            sum += (dp[x][y2] - dp[x][y1 - 1]);
        }
        return sum;
    }
}