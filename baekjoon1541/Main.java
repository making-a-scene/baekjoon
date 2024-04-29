import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        int result = 0;
        int plus = 0;
        List<Integer> nums = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : input) {
            if (c == '+' || c == '-') {
                int n = Integer.parseInt(sb.toString());
                sb.setLength(0);
                plus += n;
                if (c == '-') {
                    nums.add(plus);
                    plus = 0;
                } 
            } else {
                sb.append(c);
            }
        }
        plus += Integer.parseInt(sb.toString());
        nums.add(plus);
        for (int i = 0; i < nums.size(); i++) {
            result = (i == 0)? nums.get(i) : result - nums.get(i);
        }

        System.out.println(result);
    }

}