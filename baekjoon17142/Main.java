import java.io.*;
import java.util.*;

class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static List<int[]> viruses = new ArrayList<>();
    static char[][] lab;
    static int N;
    static int M;
    static int answer;
    static int empty = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new char[N][N];
        answer = N * N;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lab[i][j] = st.nextToken().charAt(0);
                if (lab[i][j] == '2') {
                    viruses.add(new int[] {i, j});
                } else if (lab[i][j] == '0') {
                    empty++;
                }
            }
        }
        if (empty == 0) {
            System.out.println(0);
            return;
        }

        backtracking(0, new Stack<>());
        if (answer == N * N) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void backtracking(int depth, Stack<int[]> stack) {
        if (stack.size() == M) {
            int result = spread(stack);
            if (result > 0) {
                answer = Math.min(answer, result);
            }
            return;
        }

        for (int i = depth; i < viruses.size(); i++) {
            stack.push(viruses.get(i));
            backtracking(i + 1, stack);
            stack.pop();
            backtracking(i + 1, stack);
        }
    }

    private static int spread(Stack<int[]> stack) {
        boolean[][] visited = new boolean[N][N];
        Queue<Node> queue = new LinkedList<>();
        for (int[] start : new ArrayList<>(stack)) {
            visited[start[0]][start[1]] = true;
            queue.offer(new Node(start[0], start[1], 0));
        }

        int count = 0;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = curr.row+ dx[i];
                int nextCol = curr.col + dy[i];
                if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N && !visited[nextRow][nextCol]) {
                    if (lab[nextRow][nextCol] == '0') {
                        count++;
                        if (count == empty) {
                            return curr.time + 1;
                        }
                        visited[nextRow][nextCol] = true;
                        queue.offer(new Node (nextRow, nextCol, curr.time + 1));
                    } else if (lab[nextRow][nextCol] == '2') {
                        visited[nextRow][nextCol] = true;
                        queue.offer(new Node (nextRow, nextCol, curr.time + 1));
                    }
                }
            }
        }

        return -1;
    }

    static class Node {
        int row;
        int col;
        int time;

        Node (int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}