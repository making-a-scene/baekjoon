import java.util.*;
import java.io.*;

class Main {
    private static int A;
    private static int B;
    private static int count = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        bfs(A, 1);
        if (count == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }

    private static void bfs(long n, int k) {
        if (n == B) {
            count = Math.min(count, k);
            return;
        }
        if (n > B) {
            return;
        }
        bfs(n * 2, k + 1);
        bfs(n * 10 + 1, k + 1);
    }
}