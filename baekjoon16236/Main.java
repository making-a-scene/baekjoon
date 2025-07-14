import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] space = new int[N][N];
        int[] curr = null;
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (space[i][j] == 9) {
                    curr = new int[] {i, j};
                }
            }
        }

        int time = 0;
        int eatingCount = 0;
        int sharkSize = 2;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        while (true) { 
            Queue<Fish> q = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];
            q.offer(new Fish(curr[0], curr[1], 0));
            visited[curr[0]][curr[1]] = true;
            PriorityQueue<Fish> closestFishes = new PriorityQueue<>((f1, f2) -> (f1.row == f2.row)? f1.col - f2.col : f1.row - f2.row);
            int minDistance = 0;

            while (!q.isEmpty()) {
                Fish fish = q.poll();
                if (minDistance > 0 && minDistance != fish.distance + 1) {
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nextRow = dx[i] + fish.row;
                    int nextCol = dy[i] + fish.col;
                    if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
                        // 아기 상어보다 큰 물고기가 있어 지나갈 수 없음 or 이미 지나왔던 위치
                        if ((space[nextRow][nextCol] > sharkSize) || visited[nextRow][nextCol]) {
                            continue;
                        }
                        
                        // 먹을 수 있는 물고기 발견
                        if (space[nextRow][nextCol] > 0 && space[nextRow][nextCol] < sharkSize) {
                            closestFishes.offer(new Fish(nextRow, nextCol, fish.distance + 1));
                            minDistance = fish.distance + 1;
                        } else { // 빈공간이거나 먹을 수 없는 물고기 -> 그냥 지나가기
                            q.offer(new Fish(nextRow, nextCol, fish.distance + 1));
                        }
                        visited[nextRow][nextCol] = true;
                    }
                }
            }

            if (closestFishes.isEmpty()) {
                System.out.println(time);
                return;
            }

            Fish eatenFish = closestFishes.poll();
            space[curr[0]][curr[1]] = 0;
            space[eatenFish.row][eatenFish.col] = 9;
            curr[0] = eatenFish.row;
            curr[1] = eatenFish.col;
            eatingCount++;
            if (eatingCount == sharkSize) {
                eatingCount = 0;
                sharkSize++;
            }
            time += eatenFish.distance;
        }
    }

    static class Fish {
        int row;
        int col;
        int distance;

        Fish(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}