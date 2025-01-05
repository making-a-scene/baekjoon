import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            StringBuilder sb = new StringBuilder();
            sb.append(N - K).append("\n");
            for (int i = N; i >= K; i--) {
                sb.append(i).append(" ");
            }
            System.out.println(sb.toString());
            return;
        }

        boolean[] isVisited = new boolean[200001];
        int[] prev = new int[200001];
        Queue<Integer> q = new LinkedList<>();
        isVisited[N] = true;
        prev[N] = -1;
        q.offer(N);
        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr == K) {
                printPath(prev, N, K);
                return;
            }

            if (curr - 1 >= 0 && !isVisited[curr - 1]) {
                isVisited[curr - 1] = true;
                prev[curr - 1] = curr;
                q.offer(curr - 1);
            }
            if (curr + 1 < 200001 && !isVisited[curr + 1]) {
                isVisited[curr + 1] = true;
                prev[curr + 1] = curr;
                q.offer(curr + 1);
            }
            if (curr * 2 < 200001 && !isVisited[curr * 2]) {
                isVisited[curr * 2] = true;
                prev[curr * 2] = curr;
                q.offer(curr * 2);
            }
        }
    }

    private static void printPath(int[] prev, int start, int end) {
        List<Integer> path = new ArrayList<>();
        path.add(end);
        for (int i = end; prev[i] != start; i = prev[i]) {
            path.add(prev[i]);
        }
        path.add(start);
        
        StringBuilder sb = new StringBuilder();
        sb.append(path.size() - 1).append("\n");
        for (int i = path.size() - 1; i >= 0; i--) {
            sb.append(path.get(i)).append(" ");
        }
        System.out.println(sb.toString());
    }
}