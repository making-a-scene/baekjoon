import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] ranks = new int[N][2];
            for (int n = 0; n < N; n++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                ranks[n][0] = Integer.parseInt(st.nextToken());
                ranks[n][1] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(ranks, (o1, o2) -> o1[0] - o2[0]);
            int max = ranks[0][1];
            int count = 1;
            for (int i = 1; i < N; i++) {
                if (ranks[i][1] < max) {
                    count++;
                    max = ranks[i][1];
                }
            }
            sb.append(count).append('\n');
        }
        System.out.print(sb.toString());
    }
}