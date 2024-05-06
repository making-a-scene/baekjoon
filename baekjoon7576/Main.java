import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] box = new int[N][M];
        int numOfZero = 0;
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(input[j]);
                if (box[i][j] == 0) numOfZero++;
                else if (box[i][j] == 1) {
                    qx.offer(i);
                    qy.offer(j);
                }
            }
        }

        if (numOfZero == 0) {
            System.out.println(0);
            return;
        }

        int[] mx = {-1, 0, 1, 0};
        int[] my = {0, 1, 0, -1};
        int level = -1;
        while (!qx.isEmpty()) {
            int count = qx.size();
            for (int j = 0; j < count; j++) {
                int x = qx.remove();
                int y = qy.remove();
                for (int i = 0; i < 4; i++) {
                   if (x + mx[i] < N && x + mx[i] >= 0
                   && y + my[i] < M && y + my[i] >= 0
                   && box[x + mx[i]][y + my[i]] == 0) {
                        box[x + mx[i]][y + my[i]] = 1;
                        numOfZero--;
                        qx.offer(x + mx[i]);
                        qy.offer(y + my[i]);
                    }
                }
            }
            level++;
        }
        
        if (numOfZero != 0) {
            System.out.println(-1);
        } else {
            System.out.println(level);
        }
    }
}