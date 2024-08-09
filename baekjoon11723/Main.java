import java.io.*;
import java.util.HashSet;
import java.util.Set;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Set<Integer> set = new HashSet<>();
        Set<Integer> tempSet = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
            tempSet.add(i);
        }

        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");

            switch (input[0]) {
                case "add" -> set.add(Integer.valueOf(input[1]));
                case "remove" -> set.remove(Integer.valueOf(input[1]));
                case "check" -> {
                    if (set.contains(Integer.valueOf(input[1]))) {
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                }
                case "toggle" -> {
                    if (set.contains(Integer.valueOf(input[1]))) {
                        set.remove(Integer.valueOf(input[1]));
                    } else {
                        set.add(Integer.valueOf(input[1]));
                    }
                }
                case "all" -> set.addAll(tempSet);
                case "empty" -> set.clear();
            }
        }
        System.out.println(sb.toString());
    }
}