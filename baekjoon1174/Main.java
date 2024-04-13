import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    private static int count = 10;
    private static int N;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        if (N <= 10) {
            System.out.println(N-1);
            s.close();
            return;
        }
        for (int i = 2; count < N; i++) {
            List<Integer> nums = new ArrayList<>();
            nums.add(1);
            nums.add(0);
            test(i, 2, 1, 0, nums);
        }
        s.close();

    }

    private static void test(int goal, int depth, int p, int cn, List<Integer> nums) { // p: 앞자리에 있는 숫자 cn: 현재 자리에 있는 숫자
        if (goal < depth) {
            ++count;
            if (count == N) {
                for (int num : nums) {
                    System.out.print(num);
                }
            }
            return;
        }
        nums.remove(nums.size() - 1);
        nums.add(cn);
        if (cn + 1 >= p) {
            if (p > 0) {
                test(goal, depth + 1, cn, 0, nums);
            } else {
                return;
            }
        } else {
            test(goal, depth, p, cn + 1, nums);
        }
    }
        /*
        맨 앞자리수는 1에서 9가 될 때까지 증가. 9까지 끝나면 다음 맨 앞자리를 1로 만들고 초기화
        맨 앞자리 수를 제외한 자리는 0에서부터 앞자릿수-1이 될 때까지 증가.
        10_
         */

        

}
