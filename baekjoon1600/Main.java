import java.io.*;
import java.util.*;

class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] horseX = {2, -2, 2, -2, 1, -1, 1, -1};
    static int[] horseY = {1, -1, -1, 1, -2, 2, 2, -2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        boolean[][] isPlain = new boolean[H][W];

        for (int h = 0; h < H; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < W; w++) {
                if (st.nextToken().charAt(0) == '0') {
                    isPlain[h][w] = true;
                }
            }
        }

        if (W == 1 && H == 1) {
            System.out.println(0);
            return;
        }

        boolean[][][] visited = new boolean[H][W][K + 1];
        Queue<Status> queue = new LinkedList<>();
        queue.offer(new Status(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Status curr = queue.poll();
            if (curr.horseCount < K) {
                for (int i = 0; i < 8; i++) {
                    int nextRow = curr.row + horseX[i];
                    int nextCol = curr.col + horseY[i];
                    if (nextRow >= 0 && nextRow < H && nextCol >= 0 && nextCol < W
                            && isPlain[nextRow][nextCol] && !visited[nextRow][nextCol][curr.horseCount + 1]) {
                        if (nextRow == H - 1 && nextCol == W - 1) {
                            System.out.println(curr.moveCount + 1);
                            return;
                        }
                        visited[nextRow][nextCol][curr.horseCount + 1] = true;
                        queue.offer(new Status(nextRow, nextCol, curr.horseCount + 1, curr.moveCount + 1));
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                int nextRow = curr.row + dx[i];
                int nextCol = curr.col + dy[i];
                if (nextRow >= 0 && nextRow < H && nextCol >= 0 && nextCol < W
                        && isPlain[nextRow][nextCol] && !visited[nextRow][nextCol][curr.horseCount]) {
                    if (nextRow == H - 1 && nextCol == W - 1) {
                        System.out.println(curr.moveCount + 1);
                        return;
                    }
                    visited[nextRow][nextCol][curr.horseCount] = true;
                    queue.offer(new Status(nextRow, nextCol, curr.horseCount, curr.moveCount + 1));
                }
            }
        }

        System.out.println(-1);
    }

    static class Status {
        int row;
        int col;
        int horseCount;
        int moveCount;

        Status(int row, int col, int horseCount, int moveCount) {
            this.row = row;
            this.col = col;
            this.horseCount = horseCount;
            this.moveCount = moveCount;
        }
    }
} 