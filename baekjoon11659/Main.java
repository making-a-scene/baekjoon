import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] nums;
    static int[] cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        nums = new int[N + 1];
        cache = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        cache[1] = nums[1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            sb.append(dp(start, end)).append("\n");
        }
        System.out.println(sb.toString());
    }
    private static int dp(int start, int end) {
        if (start == end)   return nums[start];
        if (cache[end] == 0) {
            cache[end] = dp(1, end - 1) + nums[end];
        }
        if (cache[start] == 0) {
            cache[start] = dp(1, start - 1) + nums[start];
        }
        return cache[end] - cache[start] + nums[start];
    }
}
