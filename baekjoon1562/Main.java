import java.io.*;
import java.util.*;

class Main {
    static int MOD = 1000000000;
    static int N;
    static long[][] dp; // dp[i][j]: i로 시작하는 j자리 계단수의 개수
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N < 10) {
            System.out.println(0);
            return;
        }
        for (int i = 1; i <= 9; i++) {
            // dp 메소드 호출
        }

// 남은 자릿수의 개수보다 등장 안 한 숫자의 개수가 더 많은 경우
    }

    private static long dynamicProgramming(Set<Integer> notAppeared, int start, int length) {
        if (length == 1) {
            return 1;
        }
        if (length == 2) {
            if (start == 0 || start == 9) {
                return 1;
            }
            return 2;
        }
        
        if (dp[start][length] == 0) {
            boolean flag = false;
            if (start > 0) {
                if (notAppeared.contains(start - 1)) {
                    flag = true;
                    notAppeared.remove(start - 1);
                }
                dp[start][length] += (dynamicProgramming(notAppeared, start - 1, length - 1) % MOD);
                if (flag) {
                    notAppeared.add(start - 1);
                }
            }
            if (start < 9) {
                flag = false;
                if (notAppeared.contains(start + 1)) {
                    flag = true;
                    notAppeared.remove(start + 1);
                }
                dp[start][length] += (dynamicProgramming(notAppeared, start + 1, length - 1) % MOD);
                if (flag) {
                    notAppeared.add(start + 1);
                }
            }
        }
        return dp[start][length];
    }
}