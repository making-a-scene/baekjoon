import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        int count = 0;
        for (int i = N - 1, start = K; i >= 0 && start > 0; i--) {
            if (coins[i] > start)   continue;
            count += start / coins[i];
            start %= coins[i];
        }
        System.out.println(count);
        
    }
}