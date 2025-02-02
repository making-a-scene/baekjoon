import java.io.*;

class Main {
    static int[][] board = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int zeroCount = 0;
        int startRow = -1;
        int startCol = -1;
        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = input.charAt(j) - '0';
                if (board[i][j] == 0) {
                    zeroCount++;
                    if (startRow == -1) {
                        startRow = i;
                        startCol = j;
                    }
                }   
            }
        }

        if (zeroCount == 0) {
            printBoard();
            return;
        }
        sudoku(startRow, startCol, zeroCount);
        printBoard();
    }

    private static boolean sudoku(int row, int col, int leftZero) {
        if (leftZero == 0) {
            printBoard();
            System.exit(0);
        }

        for (int i = row; i < 9; i++) {
            for (int j = (i == row)? col : 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    boolean[] isImpossible = findImpossibleNumbers(i, j);
                    for (int num = 1; num <= 9; num++) {
                        if (!isImpossible[num]) {
                            board[i][j] = num;
                            if (!sudoku(i, j + 1, leftZero - 1)) {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean[] findImpossibleNumbers(int row, int col) {
        boolean[] isImpossible = new boolean[10];
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != 0) {
                isImpossible[board[row][i]] = true;
            }
            if (board[i][col] != 0) {
                isImpossible[board[i][col]] = true;
            }
        }

        int i = 0;
        int j = 0;
        if (row >= 6) {
            i = 6;
        } else if (row >= 3) {
            i = 3;
        }
        if (col >= 6) {
            j = 6;
        } else if (col >= 3) {
            j = 3;
        }

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                if (board[i + a][j + b] != 0) {
                    isImpossible[board[i + a][j + b]] = true;
                }
            }
        }

        return isImpossible;
    }

    private static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}