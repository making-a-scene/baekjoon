import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        final int[] x = {-1, 0, 1, 0};
        final int[] y = {0, 1, 0, -1};
        Stack<int[]> stack = new Stack<>();

        int a = 0; // 행
        int b = 0; // 열

        while (true) {
            for (; a < N; a++) {
                for (; b < N && map[a][b] == '0'; b++); // map[a][b] == '1'인 a, b를 찾았거나, 열 인덱스의 끝에 다다를 때까지 순회
                if (b == N) { // 현재 행의 마지막 열까지 순회한 경우
                    if (a == N - 1) { // 행 인덱스, 열 인덱스 모두 순회가 끝난 경우 -> 결과 출력 후 프로그램 종료
                        System.out.println(result.size());
                        Collections.sort(result);
                        for (int n : result) {
                            System.out.println(n);
                        }
                        return;
                    } else  b = 0; // 현재 행에 대한 열 인덱스 순회가 끝났지만 다음 행이 남은 경우 -> b를 0으로 바꾸고 다음 행 순회 시작
                } 
                else if (map[a][b] == '1') { // 1을 발견한 경우 -> stack에 push하고 순회 중단 후 bfs 수행 시작
                    stack.push(new int[] {a, b});
                    break;
                }
            }

            // bfs
            int count = 0;
            while (!stack.isEmpty()) {
                int[] index = stack.pop();
                if (map[index[0]][index[1]] == '0') continue;

                map[index[0]][index[1]] = '0';
                count++;
                for (int i = 0; i < 4; i++) {
                    if (x[i] + index[0] >= 0 &&
                    x[i] + index[0] < N &&
                    y[i] + index[1] >= 0 &&
                    y[i] + index[1] < N) {
                        if (map[x[i] + index[0]][y[i] + index[1]] == '1') {
                            stack.push(new int[] {x[i] + index[0], y[i] + index[1]});
                        }
                    }
                }
            }
            result.add(count);
        }
    }
}