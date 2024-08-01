import java.io.*;
import java.util.*;

class Main {
    static int n;
    static int m;
    static int[][] answer;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[] start = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new char[m][n];
        answer = new int[m][n];

        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = input[j].charAt(0);
                if (map[i][j] == '2') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        bfs();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '1' && answer[i][j] == 0) {
                    sb.append(-1).append(" ");
                } else{
                    sb.append(answer[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
    
    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[m][n];
        q.offer(start);
        isVisited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = dx[i] + curr[0];
                int y = dy[i] + curr[1];
                if (x >= 0 && x < m && y >= 0 && y < n && !isVisited[x][y]) {
                    isVisited[x][y] = true;
                    if (map[x][y] == '1') {
                        answer[x][y] = answer[curr[0]][curr[1]] + 1;
                        q.offer(new int[] {x, y});  
                    }
                    
                }
            }
        }
    }
}