import java.io.*;

class Main {
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            count = 0;
            plusOneTwoThree(n, 0);
            sb.append(count).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void plusOneTwoThree(int target, int sum) {
        if (target == sum) {
            count++;
            return;
        }
        if (target < sum) {
            return;
        }

        plusOneTwoThree(target, sum + 1);
        plusOneTwoThree(target, sum + 2);
        plusOneTwoThree(target, sum + 3);
    }
}