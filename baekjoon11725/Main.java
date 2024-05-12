import java.util.*;
import java.io.*;

class Main {
    static Map<Integer, Set<Integer>> nodes = new HashMap<>();
    static Set<Integer> visited = new HashSet<>();
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        result = new int[N + 2];
        nodes.put(1, new HashSet<>());
        for (int i = 2; i <= N; i++) {
            nodes.put(i, new HashSet<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            Set<Integer> children = nodes.get(a);
            children.add(b);
            nodes.put(a, children);
            children = nodes.get(b);
            children.add(a);
            nodes.put(b, children);
        }
        treeTraverse(1);
        for (int i = 2; i < result.length - 1; i++) {
            System.out.println(result[i]);
        }
    }

    private static void treeTraverse(int root) {
        visited.add(root);
        Set<Integer> children = nodes.get(root);
        if (children.isEmpty()) return;

        for (int child : children) {
            if (visited.contains(child)) {
                continue;
            }
            result[child] = root;
            treeTraverse(child);
        }
    }
}