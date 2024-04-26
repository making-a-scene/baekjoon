import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] l = new int[N - 1];
        int[] prices = new int[N];
        for (int i = 0; i < N - 1; i++) {
            l[i] = Integer.parseInt(st.nextToken());
            prices[i] = Integer.parseInt(st2.nextToken());
        }
        prices[N - 1] = Integer.parseInt(st2.nextToken());
        // int[] l = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // int[] prices = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int currPrice = prices[0];
        long currDistance = 0;
        long total = 0;
        for (int i = 1; i < prices.length; i++) {
            currDistance += l[i - 1];
            if (prices[i] < currPrice || i == prices.length - 1) {
                total += (currDistance * currPrice);
                currPrice = prices[i];
                currDistance = 0;
            } 
        }
        System.out.println(total);
    }
}