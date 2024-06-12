import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] X = new int[N];
        int[] copyOfX = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
            copyOfX[i] = X[i];
        }
        
        Arrays.sort(copyOfX);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < N; i++) {
            if (map.containsKey(copyOfX[i]))  continue;
            map.put(copyOfX[i], j);
            j++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map.get(X[i])).append(" ");
        }
        System.out.println(sb.toString());
    }
}