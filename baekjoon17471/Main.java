import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int result = Integer.MAX_VALUE;
    static int[] population;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        graph = new List[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int n = 1; n <= N; n++) {
            population[n] = Integer.parseInt(st.nextToken());
            graph[n] = new ArrayList<>();
        }

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int total = Integer.parseInt(st.nextToken());
            for (int i = 0; i < total; i++) {
                int neighbor = Integer.parseInt(st.nextToken());
                graph[n].add(neighbor);
                graph[neighbor].add(n);
            }
        }

        backtracking(1, new ArrayList<>(), new ArrayList<>(), 0, 0);

        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }

    private static void backtracking(int depth, List<Integer> A, List<Integer> B, int sumOfA, int sumOfB) {
        if (depth > N) {
            if (isConnected(A, B)) {
                result = Math.min(result, Math.abs(sumOfA - sumOfB));
            }
            return;
        }

        A.add(depth);
        sumOfA += population[depth];
        backtracking(depth + 1, A, B, sumOfA, sumOfB);

        A.remove(A.size() - 1);
        sumOfA -= population[depth];
        B.add(depth);
        sumOfB += population[depth];
        backtracking(depth + 1, A, B, sumOfA, sumOfB);
        B.remove(B.size() - 1);
    }

    private static boolean isConnected(List<Integer> A, List<Integer> B) {
        if (A.isEmpty() || B.isEmpty()) {
            return false;
        }
        
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>(A);
        q.offer(A.get(0));
        set.remove(A.get(0));
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int neighbor : graph[curr]) {
                if (!set.contains(neighbor)) {
                    continue;
                }
                set.remove(neighbor);
                q.offer(neighbor);
            }
        }
        if (!set.isEmpty()) {
            return false;
        }

        set.addAll(B);
        q.offer(B.get(0));
        set.remove(B.get(0));
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int neighbor : graph[curr]) {
                if (!set.contains(neighbor)) {
                    continue;
                }
                set.remove(neighbor);
                q.offer(neighbor);
            }
        }
        return set.isEmpty();
    }
}