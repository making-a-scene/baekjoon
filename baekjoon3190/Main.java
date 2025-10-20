import java.io.*;
import java.util.*;

class Main {
    static class Movement {
        int time;
        char direction;
        Movement(int time, char direction) {
            this.time = time;
            this.direction = direction;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Movement> movements = new PriorityQueue<>((m1, m2) -> m1.time - m2.time);
        int N = Integer.parseInt(br.readLine());
        boolean[][] hasApple = new boolean[N][N];
        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            hasApple[row - 1][col - 1] = true;
        }
        int L = Integer.parseInt(br.readLine());
        for (int l = 0; l < L; l++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            movements.add(new Movement(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
        }

        int headRow = 0;
        int headCol = 0;
        int direction = 1; // 0: 위쪽, 1: 오른쪽, 2: 아래쪽, 3: 왼쪽
        boolean[][] hasBody = new boolean[N][N];
        Deque<int[]> bodies = new ArrayDeque<>();
        hasBody[0][0] = true;
        bodies.add(new int[] {0, 0});
        for (int sec = 1; sec < N * N; sec++) {
            if (direction == 0) {
                headRow--;
            } else if (direction == 1) {
                headCol++;
            } else if (direction == 2) {
                headRow++;
            } else {
                headCol--;
            }

            if (headRow < 0 || headRow >= N || headCol < 0 || headCol >= N || hasBody[headRow][headCol]) {
                System.out.println(sec);
                return;
            }
            hasBody[headRow][headCol] = true;
            bodies.addFirst(new int[] {headRow, headCol});

            if (hasApple[headRow][headCol]) {
                hasApple[headRow][headCol] = false;
            } else {
                int[] tail = bodies.pollLast();
                hasBody[tail[0]][tail[1]] = false;
            }

            if (!movements.isEmpty() && movements.peek().time == sec) {
                Movement nextMove = movements.poll();
                direction = (nextMove.direction == 'D')? direction + 1 : direction - 1;
                if (direction == 4) {
                    direction = 0;
                } else if (direction == -1) {
                    direction = 3;
                }
            }
        }
    }
}