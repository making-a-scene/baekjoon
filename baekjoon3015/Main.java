import java.io.*;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long count = 0;
        Stack<int[]> stack = new Stack<>(); // (키, 해당 키를 가진 사람이 몇 명 연속해 있는지)
        
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());

            while (!stack.isEmpty() && stack.peek()[0] < input) {
                count += stack.pop()[1];
            }

            if (stack.isEmpty()) {
                stack.push(new int[] {input, 1});
                continue;
            }
            
            int[] top = stack.peek();
            if (top[0] == input) {
                count += top[1];
                top[1]++;
                if (stack.size() > 1) {
                    count++;
                }
            } else {
                count++;
                stack.push(new int[] {input, 1});
            }
        }

        System.out.println(count);
    }
}