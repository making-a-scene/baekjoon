import java.io.*;

class Main {
    static int N;
    static int[][] cost;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            cost[i][0] = Integer.parseInt(input[0]);
            cost[i][1] = Integer.parseInt(input[1]);
            cost[i][2] = Integer.parseInt(input[2]);
        }
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];
        System.out.println(Math.min(calculateCost(N - 1, 0), Math.min(calculateCost(N - 1, 1), calculateCost(N - 1, 2))));
    }

    private static int calculateCost(int num, int color) {
        if (dp[num][color] == 0) {
            if (color == 0) {
                dp[num][color] = Math.min(calculateCost(num - 1, 1), calculateCost(num - 1, 2)) + cost[num][0];
            } else if (color == 1) {
                dp[num][color] = Math.min(calculateCost(num - 1, 0), calculateCost(num - 1, 2)) + cost[num][1];
            } else {
                dp[num][color] = Math.min(calculateCost(num - 1, 0), calculateCost(num - 1, 1)) + cost[num][2];
            }
        }
        return dp[num][color];
    }
}