import java.io.*;

class Main {
    static int A;
    static int C;
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);
        C = Integer.parseInt(input[2]);
   
        System.out.println(calculatePower(B));
    }
    private static long calculatePower(int exponent) {
        if (exponent == 0)  return 1;
        if (exponent == 1)  return A % C;

        long divided1 = calculatePower(exponent / 2) % C;
        if (exponent % 2 == 0) {
            return (divided1 * divided1) % C;
        } else {
            long divided2 = calculatePower(exponent / 2 + 1) % C;
            return (divided1 * divided2) % C;
        }
    }
}