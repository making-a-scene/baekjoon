import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb1 = new StringBuilder();
        sb1.append("1").append(br.readLine());
        char[] s1 = sb1.toString().toCharArray();

        StringBuilder sb2 = new StringBuilder();
        sb2.append("2").append(br.readLine());
        char[] s2 = sb2.toString().toCharArray();

        System.out.println(findLongestSubstring(s1, s2));
    }

    private static int findLongestSubstring(char[] s1, char[] s2) {
        int[][] dp = new int[s1.length][s2.length];
        int max = 0;
        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] != s2[j]) continue;
                dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}