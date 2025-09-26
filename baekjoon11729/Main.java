import java.io.*;

class Main {
    static int K = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            sb.append(1).append('\n');
            sb.append(1).append(' ').append(3).append('\n');
            System.out.print(sb.toString());
            return;
        }
        if (N == 2) {
            sb.append(3).append('\n');
            sb.append(1).append(' ').append(2).append('\n');
            sb.append(1).append(' ').append(3).append('\n');
            sb.append(2).append(' ').append(3).append('\n');
            System.out.print(sb.toString());
            return;
        }
        tower(N, 1, 3);
        sb.insert(0, '\n');
        sb.insert(0, K);
        System.out.print(sb.toString());
    }

    private static void tower(int n, int from, int to) {
        if (n == 1) {
            K++;
            sb.append(from).append(' ').append(to).append('\n');
            return;
        }
        
        int temp = 6 - from - to;
        
        // n-1개의 원판을 from에서 temp로 옮기기
        tower(n - 1, from, temp);
        // n번째 원판을 from에서 to로 옮기기
        sb.append(from).append(' ').append(to).append('\n');
        K++;
        // n-1개의 원판을 temo에서 to로 옮기기
        tower(n - 1, temp, to);
    }
}