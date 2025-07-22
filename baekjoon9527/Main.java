import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(countOnes(B) - countOnes(A - 1));
    }
    
    
    
    private static long countOnes(long n) {
        int bits = Long.toBinaryString(n).length();
        long count = 0;
        for (int i = 0; i < bits; i++) {
            long groupSize = 1L << (i + 1);
            long fullGroups = n / groupSize;
            long remainder = n % groupSize;

            count += fullGroups * (1L << i);
            if (remainder >= (1L << i)) {
                count += remainder - (1L << i) + 1;
            }
        }

        return count;
    }
}
