import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            System.out.println((br.readLine().matches("^[A-F]?A+F+C+[A-F]?$"))? "Infected!" : "Good");            
        }
    }
}