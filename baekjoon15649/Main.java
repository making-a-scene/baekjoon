import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        backtracking(0, 0, new StringBuilder());
        System.out.print(sb.toString());
    }

    private static void backtracking(int bit, int depth, StringBuilder s) {
        if (depth == M) {
            sb.append(s).append('\n');
            return;
        }
        for (int i = 1; i <= N; i++) {
            if ((bit & (1 << i)) != 0) {
                continue;
            }
            bit |= (1 << i);
            backtracking(bit, depth + 1, s.append(i).append(' '));
            int len = s.length();
            s.delete(len - 2, len);
            bit &= ~(1 << i);
        }
    }
}