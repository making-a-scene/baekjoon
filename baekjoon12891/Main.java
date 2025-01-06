import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String string = br.readLine();
        int[] minNum = new int[4];
        int[] currNum = new int[4];
        int answer = 0;
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            minNum[i] = Integer.parseInt(input[i]);
        }

        for (int i = 0; i < P; i++) {
            switch (string.charAt(i)) {
                case 'A': currNum[0]++; break;
                case 'C': currNum[1]++; break;
                case 'G': currNum[2]++; break;
                case 'T': currNum[3]++;
            }
        }
        if (currNum[0] >= minNum[0] && currNum[1] >= minNum[1] && currNum[2] >=  minNum[2] && currNum[3] >= minNum[3]) {
            answer++;
        }

        for (int left = 1, right = P; right < S; left++, right++) {
            switch (string.charAt(left - 1)) {
                case 'A': currNum[0]--; break;
                case 'C': currNum[1]--; break;
                case 'G': currNum[2]--; break;
                case 'T': currNum[3]--;
            }
            switch (string.charAt(right)) {
                case 'A': currNum[0]++; break;
                case 'C': currNum[1]++; break;
                case 'G': currNum[2]++; break;
                case 'T': currNum[3]++;
            }
            if (currNum[0] >= minNum[0] && currNum[1] >= minNum[1] && currNum[2] >=  minNum[2] && currNum[3] >= minNum[3]) {
                answer++;
            }
        }

        System.out.println(answer);
    }

}