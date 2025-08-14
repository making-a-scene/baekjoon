import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] heights = new int[N + 1];
        int[] rightClosest = new int[N + 1];
        int[] leftClosest = new int[N + 1];
        int[] counts = new int[N + 1];
        String[] input = br.readLine().split(" ");
        
        for (int i = 1; i <= N; i++) {
            heights[i] = Integer.parseInt(input[i - 1]);
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            counts[i] = stack.size();
            if (!stack.isEmpty()) {
                leftClosest[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = N; i > 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            counts[i] += stack.size();
            if (!stack.isEmpty()) {
                rightClosest[i] = stack.peek();
            }
            stack.push(i);
        }

        for (int i = 1; i <= N; i++) {
            if (counts[i] == 0) {
                sb.append(0);
            } else if (leftClosest[i] == 0) {
                sb.append(counts[i]).append(' ').append(rightClosest[i]);
            } else if (rightClosest[i] == 0) {
                sb.append(counts[i]).append(' ').append(leftClosest[i]);
            } else if (rightClosest[i] - i < i - leftClosest[i]) {
                sb.append(counts[i]).append(' ').append(rightClosest[i]);
            } else {
                sb.append(counts[i]).append(' ').append(leftClosest[i]);
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}
