import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        char[][] maze = new char[N][M];
        boolean[][][] visited = new boolean[N][M][N * M];
        Queue<Status> queue = new LinkedList<>();
        visited[0][0][0] = true;
        queue.offer(new Status(0, 0, 0));

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = input.charAt(j);
            }
        } 
        if (N == 1 && M == 1) {
            System.out.println(0);
            return;
        }

        List<Status> next = new ArrayList<>();
        int minBreakCount = 0;
        while (true) {
            if (queue.isEmpty()) {
                queue.addAll(next);
                next.clear();
                minBreakCount++;
            }
            Status curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = curr.row + dx[i];
                int nextCol = curr.col + dy[i];
                if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M && !visited[nextRow][nextCol][curr.breakCount]) {
                    if (nextRow == N - 1 && nextCol == M - 1) {
                        System.out.println(curr.breakCount);
                        return;
                    }
                    visited[nextRow][nextCol][curr.breakCount] = true;
                    if (maze[nextRow][nextCol] == '0') {
                        queue.offer(new Status(nextRow, nextCol, curr.breakCount));
                    } else if (curr.breakCount < minBreakCount) {
                        queue.offer(new Status(nextRow, nextCol, curr.breakCount + 1));
                    } else {
                        next.add(new Status(nextRow, nextCol, curr.breakCount + 1));
                    }
                }
            }
        }
    }

    static class Status {
        int row;
        int col;
        int breakCount;
        Status (int row, int col, int breakCount) {
            this.row = row;
            this.col = col;
            this.breakCount = breakCount;
        }
    }
}