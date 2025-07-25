import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        long[] results = new long[3];
        long minAbsSum = Long.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            if (flag) {
                break;
            }
            for (int left = 0, right = N - 1; left < right;) {
                if (left == i) {
                    left++;
                    continue;
                } 
                if (right == i) {
                    right--;
                    continue;
                }

                long sum = arr[i] + arr[left] + arr[right];
                if (minAbsSum > Math.abs(sum)) {
                    results[0] = arr[left];
                    results[1] = arr[i];
                    results[2] = arr[right];
                    minAbsSum = Math.abs(results[0] + results[1] + results[2]);
                    if (sum == 0) {
                        flag = true;
                        break;
                    }
                }
                
                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        Arrays.sort(results);
        StringBuilder sb = new StringBuilder();
        sb.append(results[0]).append(" ").append(results[1]).append(" ").append(results[2]);
        System.out.println(sb.toString());
    }
}