import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        int frontIdx = 0;
        int rearIdx = 0;
        for (int i = 0, j = N - 1; i < j; ) {
            if (arr[i] + arr[j] == 0) {
                sb.append(arr[i]).append(" ").append(arr[j]);
                System.out.println(sb.toString());
                return;
            }

            if (Math.abs(min) > Math.abs(arr[i] + arr[j])) {
                min = arr[i] + arr[j];
                frontIdx = i;
                rearIdx = j;
            } 
            if (Math.abs(arr[i]) > Math.abs(arr[j])) {
                i++;
            } else {
                j--;
            }
        }
        sb.append(arr[frontIdx]).append(" ").append(arr[rearIdx]);
        System.out.println(sb.toString());
    }
}