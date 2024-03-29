import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static Integer[] memo; 

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Main.memo = new Integer[n + 1];
		memo[1] = 0;
		memo[0] = 0;
		System.out.println(makeOne(n));
	}

	private static int makeOne(int x) {
		if (memo[x] == null) {
			if (x % 6 == 0) {
				memo[x] = Math.min(makeOne(x - 1), Math.min(makeOne(x / 3), makeOne(x / 2))) + 1;
			} else if (x % 3 == 0) {
				memo[x] =  Math.min(makeOne(x - 1), makeOne(x / 3)) + 1;
			} else if (x % 2 == 0) {
				memo[x] = Math.min(makeOne(x - 1), makeOne(x / 2)) + 1;
			} else {
				memo[x] = makeOne(x - 1) + 1;
			}
		}
		return memo[x];
	}

}
