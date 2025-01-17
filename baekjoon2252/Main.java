import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        List<Integer> result = new ArrayList<>();
        int[] degree = new int[N + 1];
        List<Integer>[] graph = new List[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            graph[Integer.parseInt(input[0])].add(Integer.valueOf(input[1]));
            degree[Integer.parseInt(input[1])]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int curr = q.poll();
            result.add(curr);
            for (int next : graph[curr]) {
                degree[next]--;
                if (degree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n : result) {
            sb.append(n).append(" ");
        }
        System.out.println(sb.toString());
    }
}