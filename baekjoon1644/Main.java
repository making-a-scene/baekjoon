import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }
        
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= Math.sqrt(N); i++) {
            for (int j = i * 2; j <= N; j += i) {
                isPrime[j] = false;
            }
        }

        List<Integer> primeNum = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primeNum.add(i);
            }
        }

        int len = primeNum.size();
        int sum = primeNum.get(0);
        int count = 0;
        for (int i = 0, j = 0; i <= j && j < len;) {
            if (sum == N) {
                count++;
                if (j + 1 == len) {
                    break;
                }
                sum += primeNum.get(++j);
            } else if (sum < N) {
                if (j + 1 == len) {
                    break;
                }
                sum += primeNum.get(++j);
            } else {
                sum -= primeNum.get(i++);
            }
        }

        System.out.println(count);
    }
}