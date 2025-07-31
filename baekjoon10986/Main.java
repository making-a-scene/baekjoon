import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] sum = new long[N];
        int[] cnt = new int[M];
        long result = 0;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            long input = Long.parseLong(st.nextToken());
            if (i == 0) {
                sum[0] = input;
            } else {
                sum[i] = sum[i - 1] + input;
            }
            
            int mod = (int) (sum[i] % M);
            result += cnt[mod];
            if (mod == 0) {
                result++;
            }
            cnt[mod]++;
        }

        System.out.println(result);
    }
}