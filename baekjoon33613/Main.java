import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int answer = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        visited[R - 1][C - 1] = true;

        move(R - 1, C - 1, 0);

        System.out.println(answer);
    }

    private static void move(int row, int col, int count) {
        if (count % 2 == 0) {
            answer++;
        }

        // 상
        if (row - 2 >= 0) {
            if (col - 1 >= 0) {
                if (!visited[row - 2][col - 1]) {
                    visited[row - 2][col - 1] = true;
                    move(row - 2, col - 1, count + 1);
                }
                
            }
            if (col + 1 < N) {
                if (!visited[row - 2][col + 1]) {
                    visited[row - 2][col + 1] = true;
                    move(row - 2, col + 1, count + 1);
                }
            }
        }

        // 하
        if (row + 2 < N) {
            if (col - 1 >= 0) {
                if (!visited[row + 2][col - 1]) {
                    visited[row + 2][col - 1] = true;
                    move(row + 2, col - 1, count + 1);
                }
            }
            if (col + 1 < N) {
                if (!visited[row + 2][col + 1]) {
                    visited[row + 2][col + 1] = true;
                    move(row + 2, col + 1, count + 1);
                }
            }
        }

        // 좌
        if (col - 2 >= 0) {
            if (row - 1 >= 0) {
                if (!visited[row - 1][col - 2]) {
                    visited[row - 1][col - 2] = true;
                    move(row - 1, col - 2, count + 1);
                }
            }
            if (row + 1 < N) {
                if (!visited[row + 1][col - 2]) {
                    visited[row + 1][col - 2] = true;
                    move(row + 1, col - 2, count + 1);
                }
            }
        }
        
        // 우
        if (col + 2 < N) {
            if (row - 1 >= 0) {
                if (!visited[row - 1][col + 2]) {
                    visited[row - 1][col + 2] = true;
                    move(row - 1, col + 2, count + 1);
                }
            }
            if (row + 1 < N) {
                if (!visited[row + 1][col + 2]) {
                    visited[row + 1][col + 2] = true;
                    move(row + 1, col + 2, count + 1);
                }
            }
        }
    }
}