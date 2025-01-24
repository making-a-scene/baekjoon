import java.io.*;

class Main {
    static String[] arr = new String[7];
    public static void main(String[] args) throws IOException {
        arr[0] = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n";
        arr[1] = "\"재귀함수가 뭔가요?\"\n";
        arr[2] = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
        arr[3] = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
        arr[4] = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
        arr[5] = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n" ;
        arr[6] = "라고 답변하였지.\n";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        recursion(0, N, sb);
        System.out.println(sb.toString());
    }

    private static void recursion(int curr, int max, StringBuilder sb) {
        if (curr > max) {
            return;
        }

        StringBuilder underbar = new StringBuilder();
        for (int j = 0; j < 4 * curr; j++) {
            underbar.append("_");
        }
        if (curr == max) {
            sb.append(underbar);
            sb.append(arr[1]);
            sb.append(underbar);
            sb.append(arr[5]);
        } else {
            for (int i = 1; i <= 4; i++) {
                sb.append(underbar);
                sb.append(arr[i]);
            }
        }

        recursion(curr + 1, max, sb);

        sb.append(underbar);

        sb.append(arr[6]);
    }
}