import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] items = new int[N][2];
        int[] cache = new int[K + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken()); // 무게
            items[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }

        // for (int i = 0; i < N; i++) {
        //     for (int j = items[0][0] + 1; j <= K; j++) {
        //         if (j < items[i][0]) {
        //             cache[j] = cache[j - 1];
        //         } else {
        //             cache[j] = Math.max(cache[j - 1], items[i][1] + cache[j - items[i][0]]);
        //         }
        //     }
        // }
        for (int i = 0; i < N; i++) {
            for (int j = K; j - items[i][0] >= 0; j--) {
                cache[j] = Math.max(cache[j], cache[j - items[i][0]] + items[i][1]);
            }
        }
        System.out.println(cache[K]);
    }
}