import java.io.*;

class Main {
    static boolean[] isNotPrime = new boolean[246913];
    static int[] sumOfPrime = new int[246913];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        getPrime();
        getNumOfPrime();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            sb.append(sumOfPrime[n << 1] - sumOfPrime[n]).append('\n');
        }
        System.out.println(sb.toString());
        
    }

    private static void getPrime() {
        isNotPrime[0] = isNotPrime[1] = true;
        for (int i = 2; i <= Math.sqrt(isNotPrime.length); i++) {
            if (isNotPrime[i]) continue;
            for (int j = i * i; j < isNotPrime.length; j += i) {
                isNotPrime[j] = true;
            }
        }
    }

    private static void getNumOfPrime() {
        int count = 0;
        for (int i = 2; i < isNotPrime.length; i++) {
            if (!isNotPrime[i]) count++;
            sumOfPrime[i] = count;
        }
    }
}