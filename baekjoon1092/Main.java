import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        List<Integer> limit = new ArrayList<>();
        List<Integer> weight = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            limit.add(Integer.parseInt(st1.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            weight.add(Integer.parseInt(st2.nextToken()));
        }
        Collections.sort(limit, Collections.reverseOrder());
        Collections.sort(weight, Collections.reverseOrder());

        if (limit.get(0) < weight.get(0)) {
            System.out.println(-1);
            return;
        }

        int count = 0;
        for (int i = 0; ; i++) {    
            if (weight.isEmpty()) {
                System.out.println(count);
                return;
            }
            if (i == 0) count++;
            int size = weight.size();
            for (int j = 0; j < weight.size(); j++) {
                if (weight.get(j) > limit.get(i))   continue;
                weight.remove(weight.get(j));
                break;
            }
            if (i == N - 1 || size == weight.size()) i = -1;
        }
    }
}