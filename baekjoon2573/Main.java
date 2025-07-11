import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        Queue<Iceberg> q = new LinkedList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] > 0) {
                    q.offer(new Iceberg(i, j, 0));
                }
            }
        }

        System.out.println(bfs(q));
    }

    private static int bfs(Queue<Iceberg> q) {
        int currYear = 0;
        List<Iceberg> willBeMelted = new ArrayList<>();
        
        while (!q.isEmpty()) {
            Iceberg currIceberg = q.poll();
            if (currIceberg.year > currYear) {
                for (int idx = willBeMelted.size() - 1; idx >= 0 && currYear == willBeMelted.get(idx).year; idx--) {
                    arr[willBeMelted.get(idx).row][willBeMelted.get(idx).col] = 0;
                }
                if (findChunks(currIceberg) != q.size() + 1) {
                    return currIceberg.year;
                }
                currYear = currIceberg.year;
            }

            int meltingCnt = melt(currIceberg);
            if (meltingCnt >= arr[currIceberg.row][currIceberg.col]) {
                willBeMelted.add(currIceberg);
            } else {
                arr[currIceberg.row][currIceberg.col] -= meltingCnt;
                ++currIceberg.year;
                q.offer(currIceberg);
            }
        }
        
        return 0;
    }

    private static int findChunks(Iceberg start) {
        boolean[][] isVisited = new boolean[N][M];
        Queue<int[]> q2 = new LinkedList<>();
        int fractions = 1;

        q2.offer(new int[] {start.row, start.col});
        isVisited[start.row][start.col] = true;
        
        while (!q2.isEmpty()) {
            int[] currLoc = q2.poll();
            for (int k = 0; k < 4; k++) {
                int nextRow = currLoc[0] + dx[k];
                int nextCol = currLoc[1] + dy[k];
                if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M && arr[nextRow][nextCol] > 0 && !isVisited[nextRow][nextCol]) {
                    fractions++;
                    isVisited[nextRow][nextCol] = true;
                    q2.offer(new int[] {nextRow, nextCol});
                }
            }
        }

        return fractions;
    }

    private static int melt(Iceberg currIceberg) {
        int meltingCnt = 0;
        for (int i = 0; i < 4; i++) {
            if (meltingCnt >= arr[currIceberg.row][currIceberg.col]) {
                break;
            }
            int nextRow = currIceberg.row + dx[i];
            int nextCol = currIceberg.col + dy[i];
            if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
                if (arr[nextRow][nextCol] == 0) {
                    meltingCnt++;
                }
            }
        }

        return meltingCnt;
    }
    
    static class Iceberg {
        int row;
        int col;
        int year;
        
        Iceberg(int row, int col, int year) {
            this.row = row;
            this.col = col;
            this.year = year;
        }
    }
}