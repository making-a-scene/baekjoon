import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] map;
    static List<Bridge> bridges = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bridges = findIslands();
        System.out.println(makeMinBridge());
    }

    private static List<Bridge> findIslands() {
        int count = 2;
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[] {i, j});
                visited[i][j] = true;
                map[i][j] = count;
                while (!q.isEmpty()) {
                    int[] curr = q.poll();
                    boolean flag = false;
                    for (int k = 0; k < 4; k++) {
                        int nextRow = curr[0] + dx[k];
                        int nextCol = curr[1] + dy[k];
                        if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N && !visited[nextRow][nextCol]) {
                            visited[nextRow][nextCol] = true;
                            if (map[nextRow][nextCol] > 0) {
                                map[nextRow][nextCol] = count;
                                int[] neighbor = new int[] {nextRow, nextCol};
                                q.offer(neighbor);
                            } else {
                                flag = true;
                            }
                        }
                    }
                    if (flag) {
                        bridges.add(new Bridge(curr));
                    }
                }
                count++;
            }
        }
        return bridges;
    }

    private static int makeMinBridge() {
        Queue<Bridge> q = new LinkedList<>(bridges);
        while (!q.isEmpty()) {
            Bridge curr = q.poll();
            int[] last = curr.paths.get(curr.paths.size() - 1);
            for (int i = 0; i < 4; i++) {
                int nextRow = last[0] + dx[i];
                int nextCol = last[1] + dy[i];
                if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N && !curr.visited[nextRow][nextCol]) {
                    if (map[nextRow][nextCol] == 0) {
                        curr.visited[nextRow][nextCol] = true;
                        q.offer(new Bridge(curr.paths, new int[] {nextRow, nextCol}, curr.visited));
                    } else {
                        int[] first = curr.paths.get(0);
                        if (map[first[0]][first[1]] != map[nextRow][nextCol]) {
                            return curr.paths.size() - 1;
                        }
                    }
                }
            }
        }
        return N * N;
    }

    static class Bridge {
        List<int[]> paths;
        boolean[][] visited;
        Bridge(int[] path) {
            this.paths = new ArrayList<>();
            this.visited = new boolean[N][N];
            paths.add(path);
        }
        Bridge(List<int[]> paths, int[] path, boolean[][] visited) {
            this.paths = new ArrayList<>(paths);
            this.paths.add(path);
            this.visited = visited;
        }
    }
}