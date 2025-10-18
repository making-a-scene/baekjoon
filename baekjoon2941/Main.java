import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int len = input.length();
        int answer = 0;
        for (int i = 0; i < len; i++, answer++) {
            if (input.charAt(i) == 'c') {
                if (i + 1 < len && (input.charAt(i + 1) == '=' || input.charAt(i + 1) == '-')) {
                    i++;
                }
            } else if (input.charAt(i) == 'd') {
                if (i + 1 < len) {
                    if (input.charAt(i + 1) == '-') {
                        i++;
                    } else if (i + 2  < len && input.charAt(i + 1) == 'z' && input.charAt(i + 2) == '=') {
                        i += 2;
                    }
                }
            } else if (input.charAt(i) == 'l' || input.charAt(i) == 'n') {
                if (i + 1 < len && input.charAt(i + 1) == 'j') {
                    i++;
                }
            } else if (input.charAt(i) == 's' || input.charAt(i) == 'z') {
                if (i + 1 < len && input.charAt(i + 1) == '=') {
                    i++;
                }
            }
        }

        System.out.println(answer);
    }
}