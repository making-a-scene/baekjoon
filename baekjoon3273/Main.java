import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int x = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(arr);
        int front = 0;
        int rear = n - 1;
        int result = 0;
        while (front < n && rear > 0 && front < rear) {
            if (arr[front] + arr[rear] > x) {
                rear--;
            } else if (arr[front] + arr[rear] < x) {
                front++;
            } else {
                result++;
                rear--;
                front++;
            }
        }
        System.out.println(result);
    }
}