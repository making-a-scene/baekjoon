import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String input = br.readLine();
        Deque<Integer> stack = new ArrayDeque<>();
        int toRemove = K;
        for (int i = 0; i < N; i++) {
            int current = input.charAt(i) - '0';
            while (!stack.isEmpty() && toRemove > 0 && stack.peekFirst() < current) {
                stack.pollFirst();
                toRemove--;
            }
            stack.offerFirst(current);
        }

        while (toRemove-- > 0) {
            stack.pollFirst();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }
        System.out.println(sb.toString());
    }
}