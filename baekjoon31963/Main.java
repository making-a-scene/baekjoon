import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        long[] exp = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        A[0] = Integer.parseInt(st.nextToken());
        long answer = 0;
        for (int i = 1; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            double ratio =  Math.ceil(Math.log(A[i - 1] / (double)A[i]) / Math.log(2)) + exp[i - 1];            
            if (ratio > 0) {
                exp[i] = Math.max(0, (long) ratio);
                answer += exp[i];
            }
            
        }
        System.out.println(answer);
    }
}