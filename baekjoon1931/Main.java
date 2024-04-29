import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] schedule = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(schedule, (a, b) -> (a[1] != b[1])? a[1] - b[1] : a[0] - b[0]);
        int count = 1;
        int endedAt = schedule[0][1];
        for (int i = 1; i < N; i++) {
            if (schedule[i][0] >= endedAt) {
                count++;
                endedAt = schedule[i][1];
            }

        }
        System.out.println(count);

    }
}