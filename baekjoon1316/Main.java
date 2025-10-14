import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        for (int n = 0; n < N; n++) {
            Set<Character> set = new HashSet<>();
            String input = br.readLine();
            int len = input.length();
            boolean flag = true;
            char prev = input.charAt(0);

            for (int i = 1; i < len; i++) {
                char curr = input.charAt(i);
                if (prev == curr) {
                    continue;
                }
                if (set.contains(prev)) {
                    flag = false;
                    break;
                }
                set.add(prev);
                prev = curr;
            }
            if (set.contains(input.charAt(len - 1))) {
                flag = false;
            }
            
            if (flag) {
                answer++;
            }
        }
        
        System.out.println(answer);
    }
}