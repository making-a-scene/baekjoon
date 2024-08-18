import java.io.*;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String exploding = br.readLine();
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
            if (stack.size() >= exploding.length()) {
                int r = exploding.length() - 1;
                for (int j = stack.size() - 1; r >= 0; r--, j--) {
                    if (stack.get(j) != exploding.charAt(r)) {
                        break;
                    }
                }
                if (r == -1) {
                    for (int k = 0; k < exploding.length(); k++) {
                        stack.pop();
                    }
                }
            }
        }
        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < stack.size(); i++) {
                sb.append(stack.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}