import java.io.*;
import java.util.*;

class Main {
    static int M;
    static int N;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                dp[i][j] = -1;
            }
        }
        
        if (M == 1 && N == 1) {
            System.out.println(1);
            return;
        }
        dp[0][0] = 0;
        System.out.println(dynamicProgramming(M - 1, N - 1));
    }

    private static int dynamicProgramming(int row, int col) {
        if (dp[row][col] == -1) {
            dp[row][col]++;
            for (int i = 0; i < 4; i++) {
                int prevRow = row + dx[i];
                int prevCol = col + dy[i];
                if (prevRow >= 0 && prevRow < M && prevCol >= 0 && prevCol < N) {
                    if (map[prevRow][prevCol] > map[row][col]) {
                        if (prevRow == 0 && prevCol == 0) {
                            dp[row][col]++;
                        } else {
                            dp[row][col] += dynamicProgramming(prevRow, prevCol);
                        }
                    }
                }
            }
        }
        return dp[row][col];
    }
}