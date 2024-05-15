import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[] x = {-1, 0, 1, 0};
        int[] y = {0, 1, 0, -1};
        int[][] pop = new int[N][N];
        int result = 0;

        for (int i = 0; i < N; i++) {
            pop[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        while (true) {
            Queue<int[]> q = new LinkedList<>();
            boolean[][] isVisited = new boolean[N][N];
            boolean isMoved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isVisited[i][j])    continue;
                    int[] start = {i, j};
                    List<int[]> union = new ArrayList<>();
                    isVisited[i][j] = true;
                    q.add(start);
                    union.add(start);
                    
                    while (!q.isEmpty()) {
                        int[] curr = q.poll();
                        for (int r = 0; r < 4; r++) {
                            int[] neighbor = {x[r] + curr[0], y[r] + curr[1]};
                            if (neighbor[0] >= 0 && neighbor[1] >= 0 && neighbor[0] < N && neighbor[1] < N) {
                                int gap = ((int) Math.abs(pop[neighbor[0]][neighbor[1]] - pop[curr[0]][curr[1]]));
                                if (gap >= L && gap <= R && !isVisited[neighbor[0]][neighbor[1]]) {
                                    q.add(neighbor);
                                    union.add(neighbor);
                                    isVisited[neighbor[0]][neighbor[1]] = true;
                                    isMoved = true;
                                }
                            }
                        }
                    }
                    if (union.size() > 1) {
                        int unionPop = 0;
                        for (int[] country : union) {
                            unionPop += pop[country[0]][country[1]];
                        }
                        for (int[] country : union) {
                            pop[country[0]][country[1]] = unionPop / union.size();
                        }
                    }   
                }
            }
            if (isMoved) {
                result++;
            } else {
                System.out.println(result);
                return;
            }
        }
    }
}