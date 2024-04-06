import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] isAllowed = new boolean[1002];
        for (int i = 0; i < 1002; i++) {
            isAllowed[i] = true;
        }

        int[] S = new int[M];
        if (M != 0) {
            S = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        } else {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < S.length; i++) {
            isAllowed[S[i]] = false;
        }

        int min = Integer.MAX_VALUE;
        for (int x = 1; x < 1002; x++) {
            if (!isAllowed[x])  continue;
            for (int y = 1; y < 1002; y++) {
                if (!isAllowed[y])  continue;
                for (int z = 1; z < 1002; z++) {
                    if (!isAllowed[z])  continue;
                    if (Math.abs(N - x * y * z) < min)  min = Math.abs(N - x * y * z);
                }
            }
        }
        System.out.println(min);
        
    }

}