import java.io.*;
import java.util.*;

class Main {
    static int N;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        int[] plan = new int[M];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = i + 1; j <= N; j++) {
                if (input[j - 1].charAt(0) == '1') {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
        for (int from = 0; from < M - 1; from++) {
            if (plan[from] == plan[from + 1]) {
                continue;
            }
            if (!dfs(plan[from], plan[from + 1])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static boolean dfs(int from, int to) {
        Stack<Integer> queue = new Stack<>();
        boolean[] visited = new boolean[N + 1];
        queue.push(from);
        visited[from] = true;
        while (!queue.isEmpty()) {
            int curr = queue.pop();
            for (int next : graph[curr]) {
                if (next == to) {
                    return true;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    queue.push(next);
                }
            }
        }

        return false;
    }
}