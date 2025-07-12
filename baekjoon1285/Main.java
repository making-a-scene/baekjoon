import java.io.*;

class Main {
    static int N;
    static boolean[][] isHead;
    static int result = 400;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isHead = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                isHead[i][j] = (input.charAt(j) == 'H');
            }
        }

        // for (int limit = 0; limit <= N; limit++) {
        //     dfs(0, 0, limit);
        // }

        for (int bit = 0; bit < (1 << N); bit++) {
            int sum = 0;
            for (int col = 0; col < N; col++) {
                int headCount = 0;
                for (int row = 0; row < N; row++) {
                    if (((bit & (1 << row)) != 0 && !isHead[row][col]) || ((bit & (1 << row)) == 0 && isHead[row][col])) {
                        headCount++;
                    }
                }
                sum += Math.min(headCount, N - headCount);
            }
            result = Math.min(result, sum);
        }

        System.out.println(result);
    }

    private static void dfs(int bit, int rowNum, int limit) {
        if (rowNum == N) {
            return;
        }

        if (Integer.bitCount(bit) == limit) {
            result = Math.min(result, getSum());
            return;
        }

        dfs(bit, rowNum + 1, limit);

        changeRow(rowNum);
        bit |= (1 << rowNum);
        dfs(bit, rowNum + 1, limit);
        changeRow(rowNum);
    }

    private static void changeRow(int rowNum) {
        for (int col = 0; col < N; col++) {
            isHead[rowNum][col] = !(isHead[rowNum][col] == true);
        }
    }

    private static int getSum() {
        int sum = 0;
        for (int col = 0; col < N; col++) {
            int headCount = 0;
            for (int row = 0; row < N; row++) {
                if (isHead[row][col]) {
                    headCount++;
                }
            }
            sum += Math.min(headCount, N - headCount);
        }
        return sum;
    }
}