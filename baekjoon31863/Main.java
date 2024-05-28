import java.io.*;
import java.util.*;

class Main {
    static int buildings = 0;
    static int brokenBuildings = 0;
    static int[][] land;
    static boolean[][] visited;
    static int N;
    static int M;
    static final int[] mx = {-1, 0, 1, 0};
    static final int[] my = {0, 1, 0, -1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        land = new int[N][M];
        visited = new boolean[N][M];
        int centerX = 0;
        int centerY = 0;
        for (int i = 0; i < N; i++) {
            char input[] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                switch (input[j]) {
                    case '@':
                        land[i][j] = -2;
                        centerX = i; centerY = j;
                        break;
                    case '.': land[i][j] = 0; break;
                    case '*': land[i][j] = 1; buildings++; break;
                    case '#': land[i][j] = 2; buildings++; break;
                    case '|': land[i][j] = -3; break;
                }
            }
        }
        Queue<Integer[]> q = epicenter(centerX, centerY);
        while (!q.isEmpty()) {
            Integer[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                if (curr[0] + mx[i] >= 0 && curr[0] + mx[i] < N && curr[1] + my[i] >= 0 && curr[1] + my[i] < M) {
                    int value = land[curr[0] + mx[i]][curr[1] + my[i]];
                    if (visited[curr[0] + mx[i]][curr[1] + my[i]] == true || value == -3)   continue;
                    if (value > 0) {
                        if (value == 1) {
                            brokenBuildings++;
                            if (!visited[curr[0] + mx[i]][curr[1] + my[i]]) {
                                visited[curr[0] + mx[i]][curr[1] + my[i]] = true;
                                q.add(new Integer[] {curr[0] + mx[i], curr[1] + my[i]});
                            }
                        }
                        land[curr[0] + mx[i]][curr[1] + my[i]]--;
                    }
                }
            }
        }       
        StringBuilder sb = new StringBuilder();
        sb.append(brokenBuildings).append(" ").append(buildings - brokenBuildings);
        System.out.println(sb.toString());
    }

    private static Queue<Integer[]> epicenter(int x, int y) {
        Queue<Integer[]> q = new LinkedList<>();
        boolean[] breakwater = new boolean[4];
        int[] mx2 = {0, 1, 0, -1, 0, 2, 0, -2};
        int[] my2 = {1, 0, -1, 0, 2, 0, -2, 0};

        for (int i = 0; i < 4; i++) {
            if (x + mx2[i] >= 0 && x + mx2[i] < N && y + my2[i] >= 0 && y + my2[i] < M) {
                if (visited[x + mx2[i]][y + my2[i]])    continue;
                if (land[x + mx2[i]][y + my2[i]] == -3) {
                    breakwater[i] = true;
                    continue;
                }
                 
                if (land[x + mx2[i]][y + my2[i]] > 0) {
                    if (land[x + mx2[i]][y + my2[i]] == 1) {
                        brokenBuildings++;
                        if (!visited[x + mx2[i]][y + my2[i]]) {
                            visited[x + mx2[i]][y + my2[i]] = true;
                            q.add(new Integer[] {x + mx2[i], y + my2[i]});
                        }
                    }
                    land[x + mx2[i]][y + my2[i]]--;
                }
            }

                
        }

        for (int i = 4; i < 8; i++) {
            if (x + mx2[i] >= 0 && x + mx2[i] < N && y + my2[i] >= 0 && y + my2[i] < M) {
                if (breakwater[i - 4] == true || visited[x + mx2[i]][y + my2[i]] || land[x + mx2[i]][y + my2[i]] == -3)  continue;
                if (land[x + mx2[i]][y + my2[i]] > 0) {
                    if (land[x + mx2[i]][y + my2[i]] == 1) {
                        brokenBuildings++;
                        if (!visited[x + mx2[i]][y + my2[i]]) {
                            visited[x + mx2[i]][y + my2[i]] = true;
                            q.add(new Integer[] {x + mx2[i], y + my2[i]});
                        }
                    }
                    land[x + mx2[i]][y + my2[i]]--;
                }
            }
        }
        return q;
    }

}