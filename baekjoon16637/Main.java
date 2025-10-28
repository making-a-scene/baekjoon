import java.io.*;

class Main {
    static long answer = Integer.MIN_VALUE;
    static int N;
    static int[] operand;
    static char[] operator;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operand = new int[N / 2 + 1];
        operator = new char[N / 2];
        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                operand[i / 2] = input.charAt(i) - '0';
            } else {
                operator[i / 2] = input.charAt(i);
            }
        }
        backtracking(new boolean[N / 2], 0);
        System.out.println(answer);
    }

    private static void backtracking(boolean[] hasBrace, int depth) {
        if (depth == N / 2) {
            answer = Math.max(answer, calculate(hasBrace));
            return;
        }
        if (depth > 0 && !hasBrace[depth - 1]) {
            hasBrace[depth] = true;
            backtracking(hasBrace, depth + 1);
            hasBrace[depth] = false;
        }
        backtracking(hasBrace, depth + 1);
    }

    private static long calculate(boolean[] hasBrace) {
        long temp = operand[0];
        for (int i = 1; i <= N / 2; i++) {
            if (i < N / 2 && hasBrace[i]) {
                temp = operate(temp, operate(operand[i], operand[i + 1], operator[i]), operator[i - 1]);
                i++;
            } else {
                temp = operate(temp, operand[i], operator[i - 1]);
            }
        }
        return temp;
    }

    private static long operate(long a, long b, char operator) {
        if (operator == '+') {
            return a + b;
        }
        if (operator == '-') {
            return a - b;
        }
        return a * b;
    }
}