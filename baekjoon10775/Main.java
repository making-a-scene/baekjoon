import java.io.*;

class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int result = 0;
        parent = new int[G + 1];

        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }
        
        for (int i = 1; i <= P; i++) {
            int gi = Integer.parseInt(br.readLine());

            // 이미 공항이 폐쇄된 경우
            if (result > 0) {
                continue;
            }

            // 도킹시킬 수 있는 가장 큰 게이트를 찾기.
            int dockingStation = find(gi);

            // 더 이상 도킹 불가 -> 공항 폐쇄
            if (dockingStation == 0) {
                result = i - 1;
            } else {
                // 사용한 현재 게이트를 더 작은 쪽으로 병합.
                union(dockingStation, dockingStation - 1);
            }
        }

        // for문 빠져나온 후에도 result 값의 갱신이 이뤄지지 않음(== 공항이 폐쇄되지 않음)
        // -> 모든 비행기를 도킹시킬 수 있다는 뜻.
        if (result == 0) {
            result = P;
        }

        System.out.println(result);
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[a] = b;
    }
}