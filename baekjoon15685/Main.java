import java.io.*;
import java.util.*;

class Main {
    static boolean[][] isMarked = new boolean[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            markDragonCurve(x, y, d, g);
        }

        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (isMarked[i][j] && isMarked[i + 1][j] && isMarked[i][j + 1] && isMarked[i + 1][j + 1]) {
                    count++;
                }
            }
        }
        
        System.out.println(count);
    }

    private static void markDragonCurve(int x, int y, int d, int g) {
        List<int[]> dragonCurves = new ArrayList<>();
        isMarked[y][x] = true;
        dragonCurves.add(new int[] {y, x});
        if (d == 0) {
            isMarked[y][x + 1] = true;
            dragonCurves.add(new int[] {y, x + 1});
        } else if (d == 1) {
            isMarked[y - 1][x] = true;
            dragonCurves.add(new int[] {y - 1, x});
        } else if (d == 2) {
            isMarked[y][x - 1] = true;
            dragonCurves.add(new int[] {y, x - 1});
        } else {
            isMarked[y + 1][x] = true;
            dragonCurves.add(new int[] {y + 1, x});
        }

        for (int i = 1; i <= g; i++) {
            increaseGeneration(dragonCurves);
        }
    }
    
    private static void increaseGeneration(List<int[]> path) {
        int size = path.size();
        for (int i = size - 2; i >= 0; i--) {
            int[] prev = path.get(i + 1);
            int[] curr = path.get(i);
            int[] last = path.get(path.size() - 1);
            if (prev[0] == curr[0]) {
                // 왼 -> 오
                if (prev[1] + 1 == curr[1]) {
                    isMarked[last[0] + 1][last[1]] = true;
                    path.add(new int[] {last[0] + 1, last[1]});
                } else { // 오 -> 왼
                    isMarked[last[0] - 1][last[1]] = true;
                    path.add(new int[] {last[0] - 1, last[1]});
                }
            } else {
                // 위 -> 아래
                if (prev[0] + 1 == curr[0]) {
                    isMarked[last[0]][last[1] - 1] = true;
                    path.add(new int[] {last[0], last[1] - 1});
                } else { // 아래 -> 위
                    isMarked[last[0]][last[1] + 1] = true;
                    path.add(new int[] {last[0], last[1] + 1});
                }
            }
        }
    }
}