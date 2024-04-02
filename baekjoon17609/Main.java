import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            char[] cArray = br.readLine().toCharArray();
            int isPalinrome = palindrome(cArray, 0, cArray.length - 1);

            if (isPalinrome == -1) {
                System.out.println(0);
            } else {
                if ((palindrome(cArray, isPalinrome + 1, cArray.length - isPalinrome - 1) != -1) && (palindrome(cArray, isPalinrome, cArray.length - isPalinrome - 2) != -1)) {
                    System.out.println(2);
                } else {
                    System.out.println(1);
                }
            }
        }

    }

    private static int palindrome(char[] c, int a, int b) {
        if (a >= b) {
            return -1;
        }
        if (c[a] == c[b]) {
            return palindrome(c, a + 1, b - 1);
        } else {
            return a;
        }
    }
}