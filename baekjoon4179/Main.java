import java.io.*;
import java.util.*;

class Main {
    static int R;
    static int C;
    static char[][] maze;
    static List<int[]> fires = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new char[R][C];
        boolean[][] visited = new boolean[R][C];
        int startR = 0;
        int startC = 0;
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                maze[i][j] = input.charAt(j);
                if (maze[i][j] == 'J') {
                    startR = i;
                    startC = j;
                    maze[i][j] = '.';
                } else if (maze[i][j] == 'F') {
                    fires.add(new int[] {i, j});
                }
            }
        }

        if (startR == 0 || startR == R - 1 || startC == 0 || startC == C - 1) {
            System.out.println(1);
            return;
        }

        Queue<Info> queue = new LinkedList<>();
        int minute = 0;
        queue.offer(new Info(minute, startR, startC));
        visited[startR][startC] = true;
        spreadFire();
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            if (info.minute > minute) {
                minute++;
                spreadFire();
            }
            for (int k = 0; k < 4; k++) {
                int nextR = info.jRow + dx[k];
                int nextC = info.jCol + dy[k];
                if (nextR >= 0 && nextR < R && nextC >= 0 && nextC < C && maze[nextR][nextC] == '.' && !visited[nextR][nextC]) {
                    if (nextR == 0 || nextR == R - 1 || nextC == 0 || nextC == C - 1) {
                        System.out.println(minute + 2);
                        return;
                    }
                    visited[nextR][nextC] = true;
                    queue.offer(new Info(minute + 1, nextR, nextC));
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    private static void spreadFire() {
        List<int[]> spreaded = new ArrayList<>();
        for (int[] fire : fires) {
            for (int k = 0; k < 4; k++) {
                int nextR = fire[0] + dx[k];
                int nextC = fire[1] + dy[k];
                if (nextR >= 0 && nextR < R && nextC >= 0 && nextC < C && maze[nextR][nextC] == '.') {
                    maze[nextR][nextC] = 'F';
                    spreaded.add(new int[] {nextR, nextC});
                }
            }
        }
        fires.clear();
        fires.addAll(spreaded);
    }

    static class Info {
        int minute;
        int jRow;
        int jCol;
        Info (int minute, int jRow, int jCol) {
            this.minute = minute;
            this.jRow = jRow;
            this.jCol = jCol;
        }
    }
}