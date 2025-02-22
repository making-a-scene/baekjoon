import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String init = br.readLine();
        int cursor = init.length();
        LinkedList<Character> ll = new LinkedList<>();
        for (int i = 0; i < cursor; i++) {
            ll.add(init.charAt(i));
        }

        int M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.charAt(0) == 'L' && cursor > 0) {
                cursor--;
            } else if (command.charAt(0) == 'D' && cursor < init.length()) {
                cursor++;
            } else if (command.charAt(0) == 'P') {
         String c = st.nextToken();
                ll.add(cursor, c.charAt(0));
                if (cursor + 1 < init.length()) {
                    cursor++;
                }
            } else if (command.charAt(0) == 'B' && cursor > 0) {
                ll.remove(cursor - 1);
                if (cursor - 2 > 0) {
                    cursor--;
                }
            }
        }

        for (char c : ll) {
            System.out.print(c);
        }
    }
}