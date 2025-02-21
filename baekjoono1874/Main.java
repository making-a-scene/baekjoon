import java.io.*;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> s = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        int curr = 1;
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(br.readLine());
            if (flag) {
                continue;
            }
            if (curr <= k) {
                while (curr <= k) {
                    s.push(curr++);
                    sb.append('+').append('\n');
                }
                s.pop();
                sb.append('-').append('\n');
            } else {
                if (s.isEmpty() || s.peek() != k) {
                    flag = true;
                    continue;
                }
                s.pop();
                sb.append('-').append('\n');
            }
        }

        if (flag) {
            System.out.println("NO");
        } else {
            System.out.print(sb.toString());
        }
    }
}