import java.io.*;
import java.util.*;

class Main {
    static long[] x;
    static int N;
    static int C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        x = new long[N];

        for (int i = 0; i < N; i++) {
            x[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(x);
        System.out.println(binarySearch(1, x[N - 1] - x[0]));
    }
    
    private static long binarySearch(long low, long high) {
        if (low > high) {
            return high;
        }
        long mid = (low + high) >> 1;
        int count = findMaxInstallation(mid);

        if (count >= C) {
            return binarySearch(mid + 1, high);
        }
        return binarySearch(low, mid - 1);
    }

    private static int findMaxInstallation(long minDistance) {
        int count = 1;
        int prev = 0;
        for (int i = 1; i < N; i++) {
            if (x[i] - x[prev] >= minDistance) {
                count++;
                prev = i;
            }
        }
        return count;
    }
}
