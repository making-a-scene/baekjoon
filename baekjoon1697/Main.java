import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        
        System.out.println(bfs(N, K));
    }
    
    private static int bfs(int N, int K) {
        if (N == K) return 0;
        int[] times = new int[100001]; 
        Arrays.fill(times, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        times[N] = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentTime = times[current];
            
            int[] nextPositions = {current - 1, current + 1, current << 1};
            for (int next : nextPositions) {
                if (next >= 0 && next <= 100000 && currentTime + 1 < times[next]) {
                    times[next] = currentTime + 1;
                    queue.offer(next);
                }
            }
        }
        
        return times[K];
    }
}
