import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine();
        String P = br.readLine();

        int n = T.length();
        int m = P.length();

        // 실패 함수 만들기
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && P.charAt(i) != P.charAt(j)) {
                j = pi[j - 1];
            }
            if (P.charAt(i) == P.charAt(j)) {
                pi[i] = ++j;
            }
        }

        // 매칭
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && T.charAt(i) != P.charAt(j)) {
                j = pi[j - 1];
            }
            if (T.charAt(i) == P.charAt(j)) {
                if (j == m - 1) {
                    count++;
                    sb.append(i - m + 2).append(' ');
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        System.out.println(count);
        if (count > 0) System.out.println(sb.toString());
    }
}