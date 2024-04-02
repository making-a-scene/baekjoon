import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cArray = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        boolean isTag = false;
        for (char c : cArray) {
            if (c == '<') {
                isTag = true;
                while (!stack.isEmpty()) {
                    System.out.print(stack.pop());
                }
                System.out.print(c);
            } else if (c == '>') {
                isTag = false;
                System.out.print(c);
            } else if (isTag) {
                System.out.print(c);
            } else if (c == ' ') {
                while (!stack.isEmpty()) {
                    System.out.print(stack.pop());
                } 
                System.out.print(c);
            } else {
                stack.add(c);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}