import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        V = Integer.parseInt(in[2]);
        List<Integer>[] edges = new List[N+1];

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            edges[Integer.parseInt(in[0])].add(Integer.parseInt(in[1]));
            edges[Integer.parseInt(in[1])].add(Integer.parseInt(in[0]));
        }
        for (int i = 1; i <= N; i++) {
            if (edges[i].size() < 2) continue;
            Collections.sort(edges[i]);
        }
        System.out.println(dfs(edges));
        System.out.println(bfs(edges));
    }

    private static String bfs(List<Integer>[] edges) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        q.add(V);
        while (!q.isEmpty()) {
            int node = q.poll();
            if (visited.contains(node)) continue;
            visited.add(node);
            sb.append(node + " ");
            q.addAll(edges[node]);
        }
        return sb.toString();
    }

    private static String dfs(List<Integer>[] edges) {
        Stack<Integer> s = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        s.add(V);
        while (!s.isEmpty()) {
            int node = s.pop();
            if (visited.contains(node))  continue;
            visited.add(node);
            sb.append(node + " ");
            for (int i = edges[node].size() - 1; i >= 0; i--) {
                if (visited.contains(edges[node].get(i)))   continue;
                s.add(edges[node].get(i));
            }
        }
        return sb.toString();
    }
}