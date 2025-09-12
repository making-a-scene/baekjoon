import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> positive = new PriorityQueue<>();
        PriorityQueue<Integer> negative = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (positive.isEmpty()) {
                    if (negative.isEmpty()) {
                        sb.append(0);
                    } else {
                        sb.append(negative.poll());
                    }
                } else {
                    if (negative.isEmpty()) {
                        sb.append(positive.poll());
                    } else {
                        if (positive.peek() >= Math.abs(negative.peek())) {
                            sb.append(negative.poll());
                        } else {
                            sb.append(positive.poll());
                        }
                    }
                }
                sb.append('\n');
            } else if (x > 0) {
                positive.offer(x);
            } else {
                negative.offer(x);
            }   
        }

        System.out.print(sb.toString());
    }
}