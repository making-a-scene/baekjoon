import java.io.*;
import java.util.*;

class Main {
    static int n;
    static int m;
    static char[][] arr;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        int count = 0;
        int maxSize = 0;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = st.nextToken().charAt(0);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '1' && !visited[i][j]) {
                    count++;
                    maxSize = Math.max(maxSize, bfs(i, j));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count).append('\n').append(maxSize);
        System.out.println(sb.toString());
    }

    private static int bfs(int row, int col) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {row, col});
        visited[row][col] = true;
        int size = 1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = curr[0] + dx[i];
                int nextCol = curr[1] + dy[i];
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m && arr[nextRow][nextCol] == '1' && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[] {nextRow, nextCol});
                    size++;
                }
            }
        }

        return size;
    }
}