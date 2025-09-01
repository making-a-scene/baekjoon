import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        final long MOD = 1000000007;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        long[] pow2 = new long[N];
        pow2[0] = 1;
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i > 0) {
                pow2[i] = (pow2[i - 1] * 2) % MOD;
            }
        }

        Arrays.sort(arr);
        long answer = 0;
        for (int i = 0; i < N; i++) {
            answer = (answer + arr[i] * (pow2[i] - pow2[N - 1 - i])) % MOD;
        }

        System.out.println(answer);
    }
}