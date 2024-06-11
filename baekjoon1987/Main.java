import java.util.*;
import java.io.*;

class Main {
    static int R;
    static int C;
    static char[][] board;
    static int[] row = {1, 0, -1, 0};
    static int[] col = {0, -1, 0, 1};
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }
        int history = 1 << (board[0][0] - 'A');
        dfs(0, 0, history, 1);
        System.out.println(max);
    }

    private static void dfs(int x, int y, int history, int length) {
        max = Math.max(max, length);
        for (int i = 0; i < 4; i++) {
            int nx = x + row[i];
            int ny = y + col[i];
            if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                int next = (1 << board[nx][ny] - 'A');
                if ((history & next) == 0) {
                    dfs(nx, ny, history | next, length + 1);
                }
            }
        }
    }
}
