import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> trees = new HashMap<>();

        int count = 0;
        while (scanner.hasNext()) {
            count++;
            String tree = scanner.nextLine();
            if (trees.containsKey(tree)) {
                trees.put(tree, trees.get(tree) + 1);
            } else {
                trees.put(tree, 1);
            }
        }

        List<String> sortedKeys = new ArrayList<>(trees.keySet());
        Collections.sort(sortedKeys);

        for (String tree : sortedKeys) {
            double result = (double) trees.get(tree) / (double) count * 100;
            System.out.println(tree + " " + String.format("%.4f", result));
        }
        scanner.close();
        
    }
}