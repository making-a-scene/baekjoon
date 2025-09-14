import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int K;
    static int[] c;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        c = new int[N + 1];
        parent = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        
        Map<Integer, int[]> map = new HashMap<>(); // (속해 있는 아이 수, 사탕 수)
        for (int i = 1; i <= N; i++) {
            if (!map.containsKey(find(i))) {
                map.put(parent[i], new int[2]);
            }
            map.get(parent[i])[0]++;
            map.get(parent[i])[1] += c[i];
        }
        
        int length = map.size();
        int[] dp = new int[K];
        int[][] children = new int[length + 1][2];
        int idx = 1;
        for (int[] child : map.values()) {
            children[idx][0] = child[0];
            children[idx++][1] = child[1];
        }
        for (int i = 1; i <= length; i++) {
            for (int j = K - 1; j >= children[i][0]; j--) {
                dp[j] = Math.max(dp[j], children[i][1] + dp[j - children[i][0]]);
            }
        }
        
        System.out.println(dp[K - 1]);
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
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}