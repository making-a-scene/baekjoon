import java.io.*;
import java.util.*;

class Main {
    static int target;
    static char[] N;
    static boolean[] broken;
    static int minGap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        target = Integer.parseInt(input);
        N = input.toCharArray();
        int M = Integer.parseInt(br.readLine());
        minGap = Math.abs(target - 100);
        broken = new boolean[10];
        if (M > 0)  {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        if (target == 100) {
            System.out.println(0);
            return;
        }
        if (M == 10) {
            System.out.println(Math.abs(100 - target));
            return;
        }

        search(0, 0);
        System.out.println(minGap);
    }

    private static void search(int num, int depth) {
        int next = 0;
        for (int i = 0; i <= 9; i++) {
            if (!broken[i]) {
                next = num * 10 + i;
                minGap = Math.min(minGap, String.valueOf(next).length() + Math.abs(target - next));
                if (depth + 1 < 6) {
                    search(next, depth + 1);
                }
            }
        }
        
    }

}