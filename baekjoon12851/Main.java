import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (N >= K) {
            sb.append(N - K).append("\n").append(1);
            System.out.println(sb.toString());
            return;
        }
        int[] result = bfs(N, K);
        sb.append(result[0]).append("\n").append(result[1]);
        System.out.println(sb.toString());
    }

    private static int[] bfs(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[2]; // result[0]: 걸리는 최단 시간 / result[1]: 최단 시간 내에 도달할 수 있는 방법 수
        result[0] = Integer.MAX_VALUE;

        int[] time = new int[100001];
        Arrays.fill(time, -1);
        time[n] = 0;
        q.offer(n);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (time[curr] > result[0]) break;

            if (curr == k) {
                result[0] = time[curr];
                result[1]++;
            }
            if (curr + 1 <= 100000 && (time[curr + 1] == -1 || time[curr + 1] == time[curr] + 1)) {
                time[curr + 1] = time[curr] + 1;
                q.offer(curr + 1);
            }
            if (curr - 1 >= 0 && (time[curr - 1] == -1 || time[curr - 1] == time[curr] + 1)) {
                time[curr - 1] = time[curr] + 1;
                q.offer(curr - 1);
            }
            if ((curr << 1) <= 100000 && (time[curr << 1] == -1 || time[curr << 1] == time[curr] + 1)) {
                time[curr << 1] = time[curr] + 1;
                q.offer(curr << 1);
            }
        }

        return result;
    }
}