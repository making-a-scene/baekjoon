import java.io.*;
import java.util.*;

class Main {
    static int[] nums;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        dp = new int[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            if (isPalindrome(S, E)) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    private static boolean isPalindrome(int left, int right) {
        if (left >= right) {
            return true;
        }

        if (dp[left][right] == 1) {
            return true;
        } else if (dp[left][right] == -1){
            return false;
        }

        if (nums[left] == nums[right] && isPalindrome(left + 1, right - 1)) {
            dp[left][right] = 1;
            return true;
        }

        dp[left][right] = -1;
        return false;
    }
}