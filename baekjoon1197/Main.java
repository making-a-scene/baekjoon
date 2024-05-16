import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        int result = 0;

        List<List<int[]>> edges = new ArrayList<>(V + 1);
        for (int i = 0; i <= V; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            String[] edgeInfo = br.readLine().split(" ");
            int A = Integer.parseInt(edgeInfo[0]);
            int B = Integer.parseInt(edgeInfo[1]);
            int C = Integer.parseInt(edgeInfo[2]);
            edges.get(A).add(new int[]{B, C});
            edges.get(B).add(new int[]{A, C});
        }
        if (V == 1) {
            System.out.println(edges.get(1).get(0)[1]);
            return;
        }

        Set<Integer> visited = new HashSet<>();
        visited.add(1);
        while (visited.size() < V) {
            int minWeight = Integer.MAX_VALUE;
            int minNode = 0;
            
            for (int node : visited) {
                for (int[] neighbor : edges.get(node)) {
                    int neighborNode = neighbor[0];
                    int weight = neighbor[1];
                    
                    if (visited.contains(neighborNode)) continue;
                    
                    if (weight < minWeight) {
                        minWeight = weight;
                        minNode = neighborNode;
                    }
                }
            }
            result += minWeight;
            visited.add(minNode);
        }
        
        System.out.println(result);
        /* 
         * visited에 있는 모든 각 노드들에 대해 이웃 노드 확인
         * 이미 visited에 있는 이웃 노드면 continue
         * 새로운 이웃 노드면 가중치 확인 -> 최솟값 갱신
         * 가중치가 최소가 되는 노드를 visited에 추가
         * 
         */
    }
}