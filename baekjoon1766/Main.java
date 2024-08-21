import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<Integer> probs[] = new Set[N + 1];
        int[] edgeCount = new int [N + 1];
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            probs[i] = new HashSet<>();
        }
        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            probs[Integer.parseInt(input[0])].add(Integer.valueOf(input[1]));
            edgeCount[Integer.parseInt(input[1])]++;
        }
        

        for (int i = 1; i <= N; i++) {
            if (edgeCount[i] == 0) {
                pq.offer(i);
            }
        }
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr).append(" ");

            for (int prob : probs[curr]) {
                edgeCount[prob]--;
                if (edgeCount[prob] == 0) {
                    pq.offer(prob);
                }
            }
        }
        System.out.println(sb.toString());
    }
}