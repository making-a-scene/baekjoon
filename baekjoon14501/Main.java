import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] schedule = new int[n + 1][2];
        int[][] cache = new int[n + 1][n + 1];
        int[][] memo = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            schedule[i][0] = scanner.nextInt();
            schedule[i][1] = scanner.nextInt();
        }
        for (int i = 1; i < n + 1; i++) {
            if (i >= schedule[1][0]) {
                cache[1][i] = schedule[1][1];
            } else {
                cache[1][i] = 0;
            }
        }
        for (int i = 1; i < n + 1; i++) {
            if (i + schedule[i][0] - 1 <= n) {
                memo[i + schedule[i][0] - 1][i] = 1;
            }
        }
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (memo[j][i] == 1) {
                    cache[i][j] = Math.max(cache[i - 1][j], schedule[i][1] + cache[i - 1][i - 1]);
                } else {
                    cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
                }
            }
        }
        System.out.println(cache[n][n]);
        scanner.close();
    }
}
