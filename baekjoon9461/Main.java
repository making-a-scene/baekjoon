import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long[] P = new long[101];
        P[1] = P[2] = P[3] = 1;

        for (int i = 4; i <= 100; i++) {
            P[i] = P[i - 3] + P[i - 2];
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(P[N]).append('\n');
        }

        System.out.print(sb.toString());
    }
}