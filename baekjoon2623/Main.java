import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        List<Integer>[] graph = new List[N + 1]; // graph[i]: i번 가수 다음에 나와야 하는 가수들의 번호
        int[] edges = new int[N + 1]; // i번 가수보다 먼저 나와야 하는 가수들의 수
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            String[] input = br.readLine().split(" ");
            int cnt = Integer.parseInt(input[0]);
            for (int i = 1; i < cnt; i++) {
                graph[Integer.parseInt(input[i])].add(Integer.valueOf(input[i + 1]));
                edges[Integer.parseInt(input[i + 1])]++;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (edges[i] == 0) {
                pq.offer(i);
            }
        }

        int count = 0;
        while (!pq.isEmpty()) {
            int num = pq.poll();
            sb.append(num).append('\n');
            count++;
            for (int next : graph[num]) {
                edges[next]--;
                if (edges[next] == 0) {
                    pq.offer(next);
                }
            }
        }

        if (count == N) {
            System.out.print(sb.toString());
            return;
        }
        System.out.println(0);
    }
}