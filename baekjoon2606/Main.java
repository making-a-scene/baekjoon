import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Integer[]> connection = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            Integer[] parsedInput = new Integer[2];
            parsedInput[0] = Integer.parseInt(input[0]);
            parsedInput[1] = Integer.parseInt(input[1]);
            connection.add(parsedInput);
        }

        Stack<Integer> s = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        s.add(1);
        while (!s.isEmpty()) {
            int poped = s.pop();
            visited.add(poped);
            for (Integer[] arr : connection) {
                if (arr[0] == poped || arr[1] == poped) {
                    if (arr[0] == poped && !visited.contains(arr[1])) {
                        s.add(arr[1]);
                    } else if (arr[1] == poped && !visited.contains(arr[0])) {
                        s.add(arr[0]);
                    }
                }
            }
        }
        System.out.println(visited.size() - 1); // 1번 컴퓨터 제외
    }
}