import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static final int MOD = 1000000007;
    static int N;
    static int M;
    static long[][] cache;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        if (N == 0) {
            long sum = 1;
            for (int i = 0; i < M; i++) {
                sum = (sum * 26) % MOD;
            }
            System.out.println(sum);
        } else {
            long sum = 0;
            cache = new long[26][M + 1];
            for (int i = 0; i < 26; i++) {
                cache[i][1] = 1;
            }
            for (int i = 0; i < 13; i++) {
                sum += (dp(i, M) % MOD);
                sum += (dp(25 - i, M) % MOD);
                sum %= MOD;
            }
            System.out.println(sum);
        }
    }

    private static long dp(int start, int length) {
        if (length < 1) return 0;
        if (cache[start][length] == 0) {
            for (int i = start + N; i < 26; i++) {
                cache[start][length] += (dp(i, length - 1) % MOD);
            }
            for (int i = start - N; i >= 0; i--) {
                cache[start][length] += (dp(i, length - 1) % MOD);
            }
        }
        return cache[start][length];
    }
}