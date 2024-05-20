import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] cache = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        cache[0] = arr[0];
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            cache[i] = Math.max(cache[i - 1] + arr[i], arr[i]);
            max = Math.max(max, cache[i]);
        }
        System.out.println(max);
    }
}