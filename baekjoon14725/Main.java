import java.io.*;
import java.util.*;

class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node root = new Node("root");
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            Node curr = root;
            for (int j = 0; j < K; j++) {
                String foodName = st.nextToken();
                // 입력으로 주어진 이름의 개미굴이 현재 층에 존재하지 않으면 새롭게 추가
                if (!curr.childrenNodes.containsKey(foodName)) {
                    curr.childrenNodes.put(foodName, new Node(foodName));
                }
                // 한 층 내려가기
                curr = curr.childrenNodes.get(foodName);
            }
        }
        buildStructure(root, -1);
        System.out.print(sb.toString());
    }

    private static void buildStructure(Node node, int depth) {
        // 리프 노드까지 모두 순회했으면 종료
        if (node.childrenNodes.isEmpty()) {
            return;
        }
        // depth * 2만큼의 '-'를 추가 후 현재 굴의 먹이 정보 추가 -> 자식 노드(아래 층)으로 이동
        for (Node child : node.childrenNodes.values()) {
            for (int cnt = 0; cnt <= (depth << 1) + 1; cnt++) {
                sb.append('-');
            }
            sb.append(child.food).append('\n');
            buildStructure(child, depth + 1);
        }
    }

    static class Node {
        String food;
        Map<String, Node> childrenNodes;

        Node (String food) {
            this.food = food;
            childrenNodes = new TreeMap<>(); // TreeMap을 사용해 key 값인 먹이 이름 순으로 정렬 유지
        }
    }
}