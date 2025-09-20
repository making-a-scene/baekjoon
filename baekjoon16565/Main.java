import java.io.*;

class Main {
    static int[] fac;
    static final int MOD = 10_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N < 4) {
            System.out.println(0);
            return;
        }
        if (N == 4) {
            System.out.println(13);
            return;
        }
        if (N == 52) {
            System.out.println(1);
            return;
        }

        fac = new int[53];
        fac[0] = 1;
        for (int i = 1; i <= 52; i++) {
            fac[i] = (fac[i - 1] * i) % MOD;
        }

        int answer = 0;
        for (int m = 1; m <= N / 4; m++) {
            int temp = combination(13, m);
            if (N - 4 * m > 0) {
                temp *= combination(52 - 4 * m, N - 4 * m);   
            }
            if (m % 2 == 1) {
                answer += temp;
            } else {
                answer -= temp;
            }
            answer %= MOD;
        }
        if (answer < 0) {
            answer += MOD;
        }
        System.out.println(answer);
    }

    private static int combination(int n, int r) {
        return (fac[n] * modInverse((fac[r] * fac[n - r]) % MOD)) % MOD;
    }

    private static int modInverse(int x) {
        int y = MOD - 2;
        int result = 1;

        while (y > 0) {
            if (y % 2 == 1) {
                result = (result * x) % MOD;
            }
            x = (x * x) % MOD;
            y >>= 1;
        }
        return result;
    }
}