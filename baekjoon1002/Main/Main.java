package Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            String[] splitedInput = input.split(" ");
            int x1 = Integer.parseInt(splitedInput[0]);
            int y1 = Integer.parseInt(splitedInput[1]);
            int r1 = Integer.parseInt(splitedInput[2]);
            int x2 = Integer.parseInt(splitedInput[3]);
            int y2 = Integer.parseInt(splitedInput[4]);
            int r2 = Integer.parseInt(splitedInput[5]);
            
            double d = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
            System.out.println(guessNumOfSpots(d, r1, r2));
        }
    }
    
    private static int guessNumOfSpots(double d, int r1, int r2) {
        if (d == 0 && r1 == r2) {
            return -1;
        } else if (r1 + r2 < d || d < Math.abs(r1 - r2)) {
            return 0;
        } else if (r1 + r2 == d || d == Math.abs(r1 - r2)) {
            return 1;
        } else {
            return 2;
        }
    }
}