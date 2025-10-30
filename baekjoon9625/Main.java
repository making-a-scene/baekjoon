import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = 1;
        int B = 0;
        int K = Integer.parseInt(br.readLine());
        for (int i = 1; i <= K; i++) {
            int tempA = A;
            int tempB = B;
            B += tempA; // A가 B로 바뀜
            A = tempB; // B가 BA로 바뀜
        }
        StringBuilder sb = new StringBuilder();
        sb.append(A).append(' ').append(B);
        System.out.println(sb.toString());
    }
}