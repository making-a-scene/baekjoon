import java.io.*;

class Main {
    static int[] nums;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        dp = new int[N]; // dp[i]: nums[0] ~ nums[i] 범위의 부분 수열 중 nums[i]를 포함하면서 가장 긴 증가하는 부분 수열의 길이
        String[] input = br.readLine().split(" ");
        
        int maxLength = 0;
        int maxVal = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(input[i]);

            if (i == 0) {
                maxLength = 1;
                maxVal = nums[0];
                continue;
            }

            if (nums[i] > maxVal) {
                maxVal = nums[i];
                dp[i] = ++maxLength;
            } else {
                int found = findIncreasingSet(i);
                if (found > maxLength) {
                    maxLength = found;
                    maxVal = nums[i];
                }
            }
        }
        System.out.println(maxLength);
    }

    // 현재 위치의 숫자를 택했을 때 부분 수열의 증가가 유지된다면 선택하면 됨.
    // 그런데 유지되지 않는다면? 현재 위치의 숫자를 버리고 기존의 증가하는 부분 수열을 택하거나,
    // 아니면 현재 위치의 숫자를 취하고 그 숫자를 포함하는 새로운 증가하는 부분 수열을 찾거나.

    private static int findIncreasingSet(int end) {
        if (end == 0) {
            return 1;
        } 
        if (dp[end] == 0) {
            int max = 0;
            for (int i = end - 1; i >= -1; i--) {
                if (i == -1)    max = Math.max(max, 1);
                else if (nums[i] < nums[end]) {
                    max = Math.max(findIncreasingSet(i) + 1, max);
                }
            }
            dp[end] = max;
        }
        return dp[end];
    }
}