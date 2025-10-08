import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        List<String> list = new ArrayList<>(set);
        Collections.sort(list, 
                (o1, o2) -> (o1.length() == o2.length())? o1.compareTo(o2) : o1.length() - o2.length());
        
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append('\n');
        }
        System.out.print(sb.toString());
    }
}