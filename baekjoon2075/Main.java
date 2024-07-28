import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (String s : input) {
                pq.offer(Integer.parseInt(s));
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = pq.poll();
        }
        System.out.println(answer);
    }
}