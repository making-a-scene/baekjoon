import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            sb.append(bfs(A, B)).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static StringBuilder bfs(int start, int end) {
        boolean[] visited = new boolean[10000];
        visited[start] = true;
        Queue<Log> q = new LinkedList<>();
        q.add(new Log(start, new StringBuilder()));
        StringBuilder result = new StringBuilder();
        while (!q.isEmpty()) {
            Log curr = q.poll();
            
            if (curr.num == end) {
                result = curr.history;
                break;
            }

            if (!visited[curr.num % 1000 * 10 + curr.num / 1000]) {
                q.add(new Log(curr.num % 1000 * 10 + curr.num / 1000, new StringBuilder(curr.history).append("L")));
                visited[curr.num % 1000 * 10 + curr.num / 1000] = true;
            }
            
            if (!visited[curr.num / 10 + curr.num % 10 * 1000]) {
                q.add(new Log(curr.num / 10 + curr.num % 10 * 1000, new StringBuilder(curr.history).append("R")));
                visited[curr.num / 10 + curr.num % 10 * 1000] = true;
            }
            
            if (curr.num == 0) {
                if (!visited[9999]) {
                    q.add(new Log(9999, new StringBuilder(curr.history).append("S")));
                    visited[9999] = true;
                }
            } else {
                if (!visited[curr.num - 1]) {
                    q.add(new Log(curr.num - 1, new StringBuilder(curr.history).append("S")));
                    visited[curr.num - 1] = true;
                }
            }

            if (!visited[(curr.num << 1) % 10000]) {
                q.add(new Log((curr.num << 1) % 10000, new StringBuilder(curr.history).append("D")));
                visited[(curr.num << 1) % 10000] = true;
            }
        }
        return result;
    }

    static class Log {
        int num;
        StringBuilder history;
        Log(int num, StringBuilder sb) {
            this.num = num;
            this.history = sb;
        }
    }
}