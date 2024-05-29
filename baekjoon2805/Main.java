import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        List<Long> trees = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            trees.add(Long.parseLong(st.nextToken()));
        }

        if (N == 1) {
            System.out.println(trees.get(0) - M);
            return;
        }
        Collections.sort(trees, Collections.reverseOrder());

        int i = 1;
        long sum = 0;
        for (; i < N; i++) {
            long next = sum + (trees.get(i - 1) - trees.get(i)) * i;
            if (next == M) {
                System.out.println(trees.get(i));
                return;
            }
            if (next > M) {
                break;
            }
            sum = next;
        }
        for (long h = trees.get(i - 1) - 1; h >= 0; h--) {
            if (sum + i * (trees.get(i - 1) - h) >= M) {
                System.out.println(h);
                return;
            }
        }
    }

}