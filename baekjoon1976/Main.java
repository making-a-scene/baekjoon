import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        int[] plan = new int[M];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = i + 1; j <= N; j++) {
                if (input[j - 1].charAt(0) == '1') {
                    union(i, j);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        plan[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
            if (find(plan[i - 1]) != find(plan[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}