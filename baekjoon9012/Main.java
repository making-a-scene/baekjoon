import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
            String input = br.readLine();
            int count = 0;
            int len = input.length();
            int idx = 0;
            for (; idx < len; idx++) {
                char curr = input.charAt(idx);
                if (curr == '(') {
                    count++;
                } else {
                    if (count == 0) {
                        break;
                    }
                    count--;
                }
            }
            if (idx == len && count == 0) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.print(sb.toString());
    }
}