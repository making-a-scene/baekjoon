import java.io.*;
import java.util.*;

class Main {
    static int L;
    static int C;
    static char[] cArray;
    static List<Character> vowels = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static Map<Character, Boolean> isVisited = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cArray = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            cArray[i] = st.nextToken().charAt(0);
            isVisited.put(cArray[i], false);
            if (cArray[i] == 'a' || cArray[i] == 'e' || cArray[i] == 'i' || cArray[i] == 'o' || cArray[i] == 'u') {
                vowels.add(cArray[i]);
            } 
        }

        if (vowels.size() < 1 || C - vowels.size() < 2) {
            return;
        }

        Arrays.sort(cArray);
        backTracking(0, 0, new char[L]);
        System.out.println(sb.toString());
    }

    private static void backTracking(int depth, int start, char[] ciphertext) {
        if (depth == L) {
            int numOfVowels = 0;
            for (char c : vowels) {
                if (isVisited.get(c)) {
                    numOfVowels++;
                }
            }
            if (numOfVowels >= 1 && L - numOfVowels >= 2) {
                sb.append(ciphertext).append("\n");
            }
            return;
        } 

        for (int i = start; i < C; i++) {
            if (!isVisited.get(cArray[i])) {
                isVisited.put(cArray[i], true);
                ciphertext[depth] = cArray[i];
                backTracking(depth + 1, i + 1, ciphertext);
                isVisited.put(cArray[i], false);
            }
        }
    }
}