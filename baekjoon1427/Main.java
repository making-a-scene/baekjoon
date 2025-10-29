import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int[] arr = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            arr[i] = input.charAt(i) - '0';
        }
        Arrays.sort(arr);
        for (int i = input.length() - 1; i >= 0; i--) {
            sb.append(arr[i]);
        }
        System.out.println(sb.toString());
    }
}