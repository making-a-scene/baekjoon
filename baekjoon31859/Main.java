import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        char[] S = st.nextToken().toCharArray();
        StringBuilder sb = new StringBuilder();

        // 1. 출전자의 영어 이름에서 알파벳이 중복되지 않도록 추출한다.
        Set<Character> included = new HashSet<>();
        int abandoned = 0;
        for (char c : S) {
            if (included.contains(c) || c < 97 || c > 122) {
                abandoned++;
                continue;
            }
            included.add(c);
            sb.append(c);
        }

        // 2. 1번을 통해 만들어진 문자열 맨 뒤에 1번 과정에서 버려진 문자의 총 개수에 4를 더한 값을 붙인다.
        sb.append(abandoned + 4);

        // 3. 2번을 통해 만들어진 문자열 맨 앞에 출전 등록 번호에 1906을 더한 값을 붙인다.
        sb.insert(0, N + 1906);

        // 4. 3번을 통해 만들어진 문자열을 뒤집는다.
        sb.reverse();

        // 5. 4번을 통해 만들어진 문자열 맨 앞에 "smupc_"를 붙인다.
        sb.insert(0, "smupc_");

        System.out.println(sb.toString());
    }
}