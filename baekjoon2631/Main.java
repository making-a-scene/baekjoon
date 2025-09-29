import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] line = new int[N];
        int[] dp = new int[N]; // dp[i]: line[i]를 최댓값으로 하는 최장 길이 부분 수열
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 0; i < N; i++) {
            line[i] = Integer.parseInt(br.readLine());
            for (int j = i - 1; j >= 0; j--) {
                if (line[j] < line[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        System.out.println(N - max);
    }
}