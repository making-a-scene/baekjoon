import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Integer[][] maze = new Integer[n + 1][m + 1];
        Integer[][] visited = new Integer[n + 1][m + 1];

        int[] mx = {0, 0, 1, -1};
        int[] my = {1, -1, 0, 0};

        for (int i = 1; i < n + 1; i++) {
            String[] line = br.readLine().split("");
            for (int j = 1; j < m + 1; j++) {
                maze[i][j] = (line[j - 1].equals("1")) ? 1 : 0;
                visited[i][j] = 0;
            }
        }

        int front = 0;
        int rear = 1;
        int[][] q = new int[n * m][2];
        q[0][0] = 1;
        q[0][1] = 1;
        int level = 1;
        while (true) {
            int count = 0;
            for (int k = front; k < rear; k++) {
                int x = q[k][0];
                int y = q[k][1];
                for (int i = 0; i < 4; i++) {
                    if (x + mx[i] <= n && x + mx[i] >= 1 
                    && y + my[i] <= m && y + my[i] >= 1 
                    && maze[x + mx[i]][y + my[i]] == 1) {
                        if (x + mx[i] == n && y + my[i] == m) {
                            System.out.println(level + 1);
                            return;
                        }
                        maze[x + mx[i]][y + my[i]] = 0;
                        q[rear + count][0] = x + mx[i];
                        q[rear + count][1] = y + my[i];
                        count++;
                    }
                }
            }
            front = rear;
            rear += count;
            level++;
        }
    }
}