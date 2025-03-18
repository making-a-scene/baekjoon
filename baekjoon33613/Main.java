import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        if (N == 3) {
            if (R == 2 && C == 2) {
                System.out.println(1);
            } else {
                System.out.println(4);
            }
            return;
        }

        if (N % 2 == 1) {
            if ((R + C) % 2 == 1) {
                System.out.println((N * N) / 2);
            } else {
                System.out.println((N * N) / 2 + 1);
            }
            return;
        }

        System.out.println((N * N) / 2);
    }
}