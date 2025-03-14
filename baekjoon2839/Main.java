import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int f = N / 5; f >= 0; f--) {
            if ((N - 5 * f) % 3 == 0) {
                System.out.println(f + (N - 5 * f) / 3);
                return;
            }
        }

        System.out.println(-1);
    }
}