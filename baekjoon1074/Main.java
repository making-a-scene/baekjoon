import java.io.*;

class Main {
    static int N;
    static int r;
    static int c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        r = Integer.parseInt(input[1]);
        c = Integer.parseInt(input[2]);
        divideIntoFour(1 << N, 0, 0, 0);
    }
    private static void divideIntoFour(int width, long count, int row, int col) {
        if (width == 2) {
            if (drawZ(count, row, col)) System.exit(0);
        } else {
            if (r < row + (width >> 1)) {
                if (c < col + (width >> 1)) {
                    divideIntoFour(width >> 1, count, row, col);
                } else {
                    divideIntoFour(width >> 1, count + (width >> 1) * (width >> 1), row, col + (width >> 1));
                }
            } else {
                if (c < col + (width >> 1)) {
                    divideIntoFour(width >> 1, count + ((width >> 1) * (width >> 1) << 1), row + (width >> 1), col);
                } else {
                    divideIntoFour(width >> 1, count + (width >> 1) * (width >> 1) * 3, row + (width >> 1), col + (width >> 1));
                }
            }
        }
    }

    private static boolean drawZ(long start, int row, int col) {
        if (row == r && col == c) {
            System.out.println(start);
            return true;
        } else if (row == r && col + 1 == c) {
            System.out.println(start + 1);
            return true;
        } else if (row + 1 == r && col == c) {
            System.out.println(start + 2);
            return true;
        } else if (row + 1 == r && col + 1 == c) {
            System.out.println(start + 3);
            return true;
        } 
        return false;
    }
}