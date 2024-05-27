import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Collections;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> Di = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            Di.add(Integer.parseInt(br.readLine()));
        }

        int Y = 0;
        int days = 0;
        while (!Di.isEmpty()) {
            int P = Di.poll();
            if (P <= K) continue;

            days++;
            Y = (Y >> 1) + P;
            sb.append("\n").append(Y);

            if (P - M > K) {
                Di.add(P - M);
            }
        }
        System.out.println(days + sb.toString());
    }
}