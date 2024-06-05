import java.util.*;
import java.io.*;

class Main {
    static int N;
    static char[][] pic;
    static boolean[][] visited;
    static int[] x = {-1, 0, 1, 0};
    static int[] y = {0, 1, 0, -1};
    static int numOfR = 0;
    static int numOfG = 0;
    static int numOfB = 0;
    static int numOfRG = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pic = new char[N][N];
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            pic[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    getAdjacentColor(true, i, j, new Stack<>());
                }
            }
        }

        if (numOfR == 0) {
            if (numOfG == 0) {
                numOfRG = 0;
            } else {
                numOfRG = numOfG;
            }
        } else if (numOfG == 0) {
            if (numOfR == 0) {
                numOfRG = 0;
            } else {
                numOfRG = numOfR;
            }
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (pic[i][j] != 'B') {
                        visited[i][j] = false;
                    }
                }
            }
    
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        getAdjacentColor(false, i, j, new Stack<>());
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(numOfR + numOfG + numOfB).append(" ").append(numOfRG + numOfB);
        System.out.println(sb.toString());
    }

    private static void getAdjacentColor(boolean isNormal, int a, int b, Stack<int[]> stack) {
        stack.push(new int[] {a, b});
        visited[a][b] = true;
        if (!isNormal) {
            numOfRG++;
        } else if (pic[a][b] == 'R') {
            numOfR++;
        } else if (pic[a][b] == 'G') {
            numOfG++;
        } else if (pic[a][b] == 'B') {
            numOfB++;
        }
        bfs(isNormal, a, b, stack);
    }

    private static void bfs(boolean isNormal, int a, int b, Stack<int[]> stack) {
        while (!stack.isEmpty()) {
            int[] idx = stack.pop();
            for (int i = 0; i < 4; i++) {
                if (idx[0] + x[i] >= 0 && idx[0] + x[i] < N && idx[1] + y[i] >= 0 && idx[1] + y[i] < N) {
                    if (visited[idx[0] + x[i]][idx[1] + y[i]])    continue;

                    if (!isNormal || (pic[a][b] == pic[idx[0] + x[i]][idx[1] + y[i]])) {
                        visited[idx[0] + x[i]][idx[1] + y[i]] = true;
                        stack.add(new int[] {idx[0] + x[i], idx[1] + y[i]});
                    }
                }
            }
        }
    }
}