import java.io.*;
import java.util.*;

class Main {
    static int n;
    static int[][] arr;
    static int[][] dp;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dynamicProgramming(i, j));
            }
        }
        
        System.out.println(answer);
    }

    private static int dynamicProgramming(int row, int col) {
        if (dp[row][col] == 0) {
            dp[row][col] = 1;
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n && arr[nextRow][nextCol] > arr[row][col]) {
                    dp[row][col] = Math.max(dp[row][col], 1 + dynamicProgramming(nextRow, nextCol));
                }
            }
        }

        return dp[row][col];
    }
}