import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        if (N < 3) {
            System.out.println(0);
            return;
        }

        Arrays.sort(arr);
        Set<Long> cache = new HashSet<>();
        int count = 0;
        if (arr[0] == arr[1] + arr[2]) {
            count++;
        }
        if (arr[1] == arr[0] + arr[2]) {
            count++;
        }
        for (int i = 2; i < N; i++) {
            boolean flag = false;

            if (cache.contains(arr[i])) {
                flag = true;
            }

            int j = i - 2;
            for (; j >= 0 && arr[i - 1] + arr[j] > arr[i]; j--) {
                cache.add(arr[i] + arr[j]);
            }
            if (j >= 0 && arr[i - 1] + arr[j] == arr[i]) {
                cache.add(arr[i - 1] + arr[j]);
                flag = true;
            }

            if (flag) {
                count++;
            }
        }
        
        System.out.println(count);
    }
}