import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        backtracking(new ArrayList<>());
        System.out.print(sb.toString());
    }

    private static void backtracking(List<Integer> selected) {
        if (selected.size() == M) {
            appendArray(selected);
            return;
        }

        for (int i = 1; i <= N; i++) {
            selected.add(i);
            backtracking(selected);
            selected.remove(selected.size() - 1);
        }
    }

    private static void appendArray(List<Integer> selected) {
        for (int i = 0; i < M; i++) {
            sb.append(selected.get(i)).append(' ');
        }
        sb.append('\n');
    }
}