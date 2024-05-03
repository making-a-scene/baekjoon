import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] honey;
    static int[][] cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        honey = new int[N];
        cache = new int[N][2]; // cache[i][0]: honey[1]부터 honey[i-1]까지의 합, cache[i][1]: honey[i-1]부터 honey[N-2]까지의 합
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        int cMax = 0;
        for (int i = 0; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
            // 3
            if (i >= 1 && i <= N - 2) {
                sum += honey[i];
                cMax = Math.max(cMax, honey[i]);
            }
        }
        cMax += sum;

        // 1
        long aMax = 0;
        for (int i = 1; i < N - 1; i++) {
            long a = honey[N - 1] << 1; 
            a += getNumOfHoneys(i, false);
            a += getNumOfHoneys(i, true) << 1;
            aMax = Math.max(a, aMax);
        }

        // 2
        long bMax = 0;
        for (int i = N - 2; i >= 1; i--) {
            long b = honey[0] << 1;
            b += getNumOfHoneys(i, true);
            b += getNumOfHoneys(i, false) << 1;
            bMax = Math.max(b, bMax);
        }
        System.out.println(Math.max(aMax, Math.max(bMax, cMax)));
    }
    /*
     * 1. 벌 하나가 왼쪽 끝, 벌집이 오른쪽 끝에 있고 다른 벌은 그 사이 어딘가에 존재.
     * 2. 벌 하나가 오른쪽 끝, 벌집이 왼쪽 끝에 있고 다른 벌은 그 사이 어딘가에 존재.
     * 3. 두 벌이 양 끝에 있고, 벌집은 그 사이 칸 중 최댓값이 있는 곳에 존재.
     */

    private static int getNumOfHoneys(int idx, boolean isStart) {
        if (!isStart) { // 1부터 idx-1까지의 합
            if (idx - 1 < 1)   return 0;
            if (cache[idx][0] != 0) return cache[idx][0];
            if (idx - 1 == 1) {
                cache[idx][0] = honey[1];
                return cache[idx][0];
            }
            cache[idx][0] = getNumOfHoneys(idx - 1, isStart) + honey[idx - 1];
            return cache[idx][0];

        } else { // idx+1부터 N-2까지의 합
            if (idx + 1 > N - 2)    return 0;
            if (cache[idx][1] != 0) return cache[idx][1];
            if (idx + 1 == N - 2) {
                cache[idx][1] = honey[N - 2];
                return cache[idx][1];
            }
            cache[idx][1] = getNumOfHoneys(idx + 1, isStart) + honey[idx + 1];
            return cache[idx][1];
        }
    }

}