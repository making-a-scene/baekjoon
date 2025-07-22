import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] board;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                result = Math.max(result, board[i][j]);
            }
        }

        if (N == 1) {
            System.out.println(board[0][0]);
            return;
        }


        dfs(1, 1);
        dfs(1, 2);
        dfs(1, 3);
        dfs(1, 4);

        System.out.println(result);
    }

    private static void dfs(int depth, int move) {
        if (depth > 5) {
            return;
        }

        int[][] original = copyBoard();

        switch (move) {
            case 1: {
                moveUp();
                mergeUp();
                moveUp();
                break;
            }
            case 2: {
                moveDown();
                mergeDown();
                moveDown();
                break;
            }
            case 3: {
                moveLeft();
                mergeLeft();
                moveLeft();
                break;
            }
            case 4: {
                moveRight();
                mergeRight();
                moveRight();
                break;
            }
        }

        dfs(depth + 1, 1);
        dfs(depth + 1, 2);
        dfs(depth + 1, 3);
        dfs(depth + 1, 4);
        board = original;
    }

    private static void moveUp() {
        for (int col = 0; col < N; col++) {
            List<Integer> nums = new ArrayList<>();
            for (int row = 0; row < N; row++) {
                if (board[row][col] > 0) {
                    nums.add(board[row][col]);
                }
            }

            for (int row = 0, k = 0; row < N; row++) {
                if (k < nums.size()) {
                    board[row][col] = nums.get(k);
                    k++;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

    private static void mergeUp() {
        for (int col = 0; col < N ; col++) {
            for (int row = 0; row + 1 < N && board[row + 1][col] > 0; row++) {
                if (board[row][col] == board[row + 1][col]) {
                    board[row][col] <<= 1;
                    result = Math.max(result, board[row][col]);
                    board[row + 1][col] = 0;
                    row++;
                }
            }
        }
    }

    private static void moveDown() {
        for (int col = 0; col < N; col++) {
            List<Integer> nums = new ArrayList<>();
            for (int row = N - 1; row >= 0; row--) {
                if (board[row][col] > 0) {
                    nums.add(board[row][col]);
                }
            }

            for (int row = N - 1, k = 0; row >= 0; row--) {
                if (k < nums.size()) {
                    board[row][col] = nums.get(k);
                    k++;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

    private static void mergeDown() {
        for (int col = 0; col < N ; col++) {
            for (int row = N - 1; row - 1 >= 0 && board[row - 1][col] > 0; row--) {
                if (board[row][col] == board[row - 1][col]) {
                    board[row][col] <<= 1;
                    result = Math.max(result, board[row][col]);
                    board[row - 1][col] = 0;
                    row--;
                }
            }
        }
    }

    private static void moveLeft() {
        for (int row = 0; row < N; row++) {
            List<Integer> nums = new ArrayList<>();
            for (int col = 0; col < N; col++) {
                if (board[row][col] > 0) {
                    nums.add(board[row][col]);
                }
            }

            for (int col = 0, k = 0; col < N; col++) {
                if (k < nums.size()) {
                    board[row][col] = nums.get(k);
                    k++;
                } else {
                    board[row][col] = 0;
                }
            }
        }    
    }

    private static void mergeLeft() {
        for (int row = 0; row < N ; row++) {
            for (int col = 0; col + 1 < N && board[row][col + 1] > 0; col++) {
                if (board[row][col] == board[row][col + 1]) {
                    board[row][col] <<= 1;
                    result = Math.max(result, board[row][col]);
                    board[row][col + 1] = 0;
                    col++;
                }
            }
        } 
    }

    private static void moveRight() {
        for (int row = 0; row < N; row++) {
            List<Integer> nums = new ArrayList<>();
            for (int col = N - 1; col >= 0; col--) {
                if (board[row][col] > 0) {
                    nums.add(board[row][col]);
                }
            }

            for (int col = N - 1, k = 0; col >= 0; col--) {
                if (k < nums.size()) {
                    board[row][col] = nums.get(k);
                    k++;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

    private static void mergeRight() {
        for (int row = 0; row < N ; row++) {
            for (int col = N - 1; col - 1 >= 0 && board[row][col - 1] > 0; col--) {
                if (board[row][col] == board[row][col - 1]) {
                    board[row][col] <<= 1;
                    result = Math.max(result, board[row][col]);
                    board[row][col - 1] = 0;
                    col--;
                }
            }
        }
    }

    private static int[][] copyBoard() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }
}