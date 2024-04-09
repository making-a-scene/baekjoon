import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N;
    static int[][] SB;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        SB = new int[N][2];
        for (int i = 0; i < N; i++) {
            SB[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        dfs(0, 1, 0, 0);
        System.out.println(min);
    }

    private static void dfs(int depth, int sour, int bit, int count) {
        if (depth == N) {
            if (count != 0 && Math.abs(sour - bit) < min)   min = Math.abs(sour - bit);
            return;
        }
        dfs(depth + 1, sour * SB[depth][0], bit + SB[depth][1], count + 1);
        dfs(depth + 1, sour, bit, count);
    }
}