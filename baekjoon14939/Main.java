import java.io.*;
import java.util.*;

class Main {
    static char[][] bulb = new char[10][10];
    static int answer = 101;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            String input = br.readLine();
            for (int j = 0; j < 10; j++) {
                bulb[i][j] = input.charAt(j);
            }
        }

        backtracking(0, 0);
        
        if (answer == 101) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    private static void backtracking(int depth, int firstRowPressCnt) {
        if (depth == 10) {
            int count = calculatePressCount();
            if (count > -1) {
                answer = Math.min(answer, count + firstRowPressCnt);
            }
            return;
        }
        pressSwitch(0, depth);
        backtracking(depth + 1, firstRowPressCnt + 1);
        pressSwitch(0, depth);
        backtracking(depth + 1, firstRowPressCnt);
    }

    private static void pressSwitch(List<int[]> pressedList) {
        int[] dx = {0, -1, 0, 1, 0};
        int[] dy = {0, 0, 1, 0, -1};

        for (int[] pressed : pressedList) {
            for (int i = 0; i < 5; i++) {
                int targetRow = dx[i] + pressed[0];
                int targetCol = dy[i] + pressed[1];
                if (targetRow >= 0 && targetRow < 10 && targetCol >= 0 && targetCol < 10) {
                    bulb[targetRow][targetCol] = (bulb[targetRow][targetCol] == '#')? 'O' : '#';
                }
            }
        }
    }

        private static void pressSwitch(int row, int col) {
        int[] dx = {0, -1, 0, 1, 0};
        int[] dy = {0, 0, 1, 0, -1};

        for (int i = 0; i < 5; i++) {
            int targetRow = dx[i] + row;
            int targetCol = dy[i] + col;
            if (targetRow >= 0 && targetRow < 10 && targetCol >= 0 && targetCol < 10) {
                bulb[targetRow][targetCol] = (bulb[targetRow][targetCol] == '#')? 'O' : '#';
            }
        }
    }

    private static int calculatePressCount() {
        List<int[]> pressedList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (bulb[i - 1][j] == 'O') {
                    pressSwitch(i, j);
                    pressedList.add(new int[] {i, j});
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (bulb[i][j] == 'O') {
                    pressSwitch(pressedList);
                    return -1;
                }
            }
        }

        pressSwitch(pressedList);
        return pressedList.size();
    }
}