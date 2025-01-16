import java.io.*;
import java.util.*;

class Main {
    static int[] selection;
    static boolean[] visited;
    static boolean[] inCycle; // 사이클에 포함된 노드 표시

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            selection = new int[N + 1];
            visited = new boolean[N + 1];
            inCycle = new boolean[N + 1];
            
            for (int i = 1; i <= N; i++) {
                selection[i] = Integer.parseInt(input[i - 1]);
            }
            
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }
            
            int cycleCount = 0;
            for (int i = 1; i <= N; i++) {
                if (inCycle[i]) cycleCount++;
            }
            sb.append(N - cycleCount).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int start) {
        int curr = start;
        List<Integer> path = new ArrayList<>(); // 경로를 기록
        
        while (true) {
            if (visited[curr]) { // 이미 방문한 노드라면
                if (path.contains(curr)) { // 현재 경로에서 사이클이 있는 경우
                    int cycleStartIndex = path.indexOf(curr); // 사이클 시작점 인덱스 찾기
                    for (int i = cycleStartIndex; i < path.size(); i++) {
                        inCycle[path.get(i)] = true; // 사이클 노드 표시
                    }
                }
                return; // 탐색 종료
            }
            
            visited[curr] = true; // 방문 처리
            path.add(curr); // 경로에 추가
            curr = selection[curr]; // 다음 노드로 이동
        }
    }    
}