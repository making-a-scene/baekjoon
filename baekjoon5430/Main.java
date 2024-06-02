import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            char[] p = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            String input = br.readLine();
            String[] arr = input.substring(1, input.length() - 1).split(",");
            
            int start = 0;
            int end = n - 1; 
            boolean isReversed = false;
            boolean isError = false;
            for (char c : p) {
                if (c == 'R') {
                    isReversed = (isReversed)? false : true;
                } else {
                    if (start > end) {
                        isError = true;
                        break;
                    } else if (isReversed) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }

            if (start > end) {
                if (!isError)   sb.append("[]\n");
                else    sb.append("error\n");
                continue;
            }

            sb.append("[");
            if (isReversed) {
                for (int j = end; j >= start; j--) {
                    sb.append(arr[j]);
                    if (j == start) sb.append("]");
                    else    sb.append(",");
                }
            } else {
                for (int j = start; j <= end; j++) {
                    sb.append(arr[j]);
                    if (j == end) sb.append("]");
                    else    sb.append(",");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}