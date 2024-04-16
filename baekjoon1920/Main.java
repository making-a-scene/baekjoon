import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.readLine();
        int[] M = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        Arrays.sort(A); // nlogn

        StringBuilder sb = new StringBuilder();
        for (int num : M) { // n
            sb.append(binarySearch(A, num, 0, A.length - 1)).append('\n'); // logn
        } // nlogn
        System.out.println(sb);
    }

    private static int binarySearch(int[] arr, int target, int start, int end) {
        if (start > end) {
            return 0;
        }
        int mid = (start + end) >>> 1;
        if (arr[mid] == target) {
            return 1;
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, end);
        } else {
            return binarySearch(arr, target, start, mid - 1);
        }
    }
}