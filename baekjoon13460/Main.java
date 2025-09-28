import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static char[][] board;
    static int[] hole = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        Trial init = new Trial();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'B') {
                    init.rowB = i;
                    init.colB = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'R') {
                    init.rowR = i;
                    init.colR = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'O') {
                    hole[0] = i;
                    hole[1] = j;
                }
            }
        }

        System.out.println(bfs(init));
    }

    private static int bfs(Trial init) {
        char[] directions = {'u', 'r', 'd', 'l'};
        Queue<Trial> queue = new LinkedList<>();
        queue.offer(init);
        while (!queue.isEmpty()) {
            Trial curr = queue.poll();
            if (curr.count == 10) {
                return -1;
            }
            for (int i = 0; i < 4; i++) {
                if (curr.lastDir == directions[i]) {
                    continue;
                }

                Trial next = lean(curr, directions[i]);
                if (next.rowB == -1) {
                    continue;
                }
                if (next.rowR == hole[0] && next.colR == hole[1]) {
                    return next.count;
                }
                queue.offer(next);
            }
        }
        return -1;
    }

    private static Trial lean(Trial origin, char direction) {
        int p = (direction == 'd' || direction == 'r')? 1 : -1;
        int[] nextR;
        int[] nextB;

        if (direction == 'u' || direction == 'd') {
            if ((direction == 'u' && origin.rowR < origin.rowB) || (direction == 'd' && origin.rowR > origin.rowB)) {
                nextR = leanUpOrDown(new int[] {origin.rowR, origin.colR}, new int[] {origin.rowB, origin.colB}, p);
                nextB = leanUpOrDown(new int[] {origin.rowB, origin.colB}, nextR, p);
            } else {
                nextB = leanUpOrDown(new int[] {origin.rowB, origin.colB}, new int[] {origin.rowR, origin.colR}, p);
                nextR = leanUpOrDown(new int[] {origin.rowR, origin.colR}, nextB, p);
            }
            
        } else {
            if ((direction == 'l' && origin.colR < origin.colB) || (direction == 'r' && origin.colR > origin.colB)) {
                nextR = leanLeftOrRight(new int[] {origin.rowR, origin.colR}, new int[] {origin.rowB, origin.colB}, p);
                nextB = leanLeftOrRight(new int[] {origin.rowB, origin.colB}, nextR, p);
            } else {
                nextB = leanLeftOrRight(new int[] {origin.rowB, origin.colB}, new int[] {origin.rowR, origin.colR}, p);
                nextR = leanLeftOrRight(new int[] {origin.rowR, origin.colR}, nextB, p);
            }
        }
        if (nextB[0] == hole[0] && nextB[1] == hole[1]) {
            nextB[0] = -1;
        }
        return new Trial(nextR[0], nextR[1], nextB[0], nextB[1], origin.count + 1, direction);
    }

    private static int[] leanUpOrDown(int[] target, int[] another, int p) {
        int[] next = new int[2];
        next[1] = target[1];
        for (int i = target[0]; i >= 0 && i < N; i += p) {
            if (i == target[0]) {
                continue;
            }

            if (board[i][target[1]] == 'O') {
                next[0] = i;
                break;
            } else if (board[i][target[1]] == '#' || (i == another[0] && target[1] == another[1])) {
                next[0] = i - p;
                break;
            }
        }

        return next;
    }

    private static int[] leanLeftOrRight(int[] target, int[] another, int p) {
        int[] next = new int[2];
        next[0] = target[0];
        for (int i = target[1]; i >= 0 && i < M; i += p) {
            if (i == target[1]) {
                continue;
            }

            if (board[target[0]][i] == 'O') {
                next[1] = i;
                break;
            } else if (board[target[0]][i] == '#' || (target[0] == another[0] && i == another[1])) {
                next[1] = i - p;
                break;
            } 
        }

        return next;
    }

    static class Trial {
        int rowR;
        int colR;
        int rowB;
        int colB;
        int count;
        char lastDir;

        public Trial(int rowR, int colR, int rowB, int colB, int count, char lastDir) {
            this.rowR = rowR;
            this.colR = colR;
            this.rowB = rowB;
            this.colB = colB;
            this.count = count;
        }

        public Trial() {}
    }
}