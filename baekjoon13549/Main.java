import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        HashSet<Integer> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(n, 0));
        while (!q.isEmpty()) {
            Node next = q.poll();
            visited.add(next.val);
            if (next.val == k) {
                System.out.println(next.sec);
                return;
            }
            if (2 * next.val <= 100000 && !visited.contains(2 * next.val)) {
                q.add(new Node(2 * next.val, next.sec));
            }
            if (next.val - 1 >= 0 && !visited.contains(next.val - 1)) {
                q.add(new Node(next.val - 1, next.sec + 1));
            }
            if (next.val + 1 <= 100000 && !visited.contains(next.val + 1)) {
                q.add(new Node(next.val + 1, next.sec + 1));
            }
        }
    }

    static class Node {
        Integer val;
        Integer sec;
        public Node(Integer val, Integer sec) {
            this.val = val;
            this.sec = sec;
        }
    }
}