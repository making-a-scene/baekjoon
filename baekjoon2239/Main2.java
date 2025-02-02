import java.io.*;

class Main2 {
    static int[][] board = new int[9][9];
    static int[] rowMask = new int[9]; // 각 행에서 사용된 숫자들
    static int[] colMask = new int[9]; // 각 열에서 사용된 숫자들
    static int[][] boxMask = new int[3][3]; // 3x3 박스에서 사용된 숫자들

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int zeroCount = 0;

        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = input.charAt(j) - '0';
                if (board[i][j] != 0) {
                    int num = board[i][j];
                    int bit = 1 << num; // num에 해당하는 비트값
                    rowMask[i] |= bit;
                    colMask[j] |= bit;
                    boxMask[i / 3][j / 3] |= bit;
                } else {
                    zeroCount++;
                }
            }
        }

        if (zeroCount == 0) {
            printBoard();
            return;
        }

        sudoku(0, 0, zeroCount);
    }

    private static boolean sudoku(int row, int col, int leftZero) {
        if (leftZero == 0) {
            printBoard();
            System.exit(0);
        }

        if (col == 9) {
            return sudoku(row + 1, 0, leftZero); // 다음 행으로 이동
        }
        if (row == 9) {
            return true; // 스도쿠 해결 완료
        }
        if (board[row][col] != 0) {
            return sudoku(row, col + 1, leftZero); // 이미 숫자가 있으면 다음 칸으로
        }

        int boxRow = row / 3, boxCol = col / 3;
        int usedMask = rowMask[row] | colMask[col] | boxMask[boxRow][boxCol]; // 사용 불가능한 숫자
        int possibleNumbers = (~usedMask) & 0b1111111110; // 1~9에서 가능한 숫자 추출

        for (int num = 1; num <= 9; num++) {
            int bit = 1 << num;
            if ((possibleNumbers & bit) != 0) { // num이 사용 가능하면 배치
                board[row][col] = num;
                rowMask[row] |= bit;
                colMask[col] |= bit;
                boxMask[boxRow][boxCol] |= bit;

                if (sudoku(row, col + 1, leftZero - 1)) {
                    return true;
                }

                // 백트래킹 (되돌리기)
                board[row][col] = 0;
                rowMask[row] &= ~bit;
                colMask[col] &= ~bit;
                boxMask[boxRow][boxCol] &= ~bit;
            }
        }

        return false; // 해결 불가능한 경우
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