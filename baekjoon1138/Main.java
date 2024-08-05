import java.io.*;
import java.util.LinkedList;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        LinkedList<Integer> ll = new LinkedList<>();
        ll.offer(N);
        for (int i = N - 2; i >= 0; i--) {
            ll.add(Integer.parseInt(input[i]), i + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ll.get(i)).append(" ");
        }
        System.out.println(sb.toString());     
    }
}