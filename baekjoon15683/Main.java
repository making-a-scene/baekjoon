import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static char[][] office;
    static List<CCTV> CCTVs = new ArrayList<>();
    static int result = 64;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new char[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = st.nextToken().charAt(0);
                if (office[i][j] > '0' && office[i][j] < '6') {
                    CCTVs.add(new CCTV(i, j, office[i][j] - '0'));
                }
            }
        }

        backtracking(0);
        System.out.println(result);
    }

    private static void backtracking(int depth) {
        if (depth == CCTVs.size()) {
            result = Math.min(result, findBlindSpots());
            return;
        }

        CCTV curr = CCTVs.get(depth);
        int dirLimit = 4;
        if (curr.num == 2) {
            dirLimit = 2;
        } else if (curr.num == 5) {
            dirLimit = 1;
        }
        
        for (int dir = 1; dir <= dirLimit; dir++) {
            curr.changeDirection(dir);
            backtracking(depth + 1);
        }
    }

    private static int findBlindSpots() {
        char[][] copied = new char[N][M];
        for (int i = 0; i < N; i++) {
            copied[i] = new char[M];
            System.arraycopy(office[i], 0, copied[i], 0, M);
        }

        for (CCTV cctv : CCTVs) {
            if (cctv.up) {
                for (int i = cctv.row - 1; i >= 0; i--) {
                    if (copied[i][cctv.col] == '6') {
                        break;
                    }
                    if (copied[i][cctv.col] == '0') {
                        copied[i][cctv.col] = '7';
                    }
                }
            }
            if (cctv.down) {
                for (int i = cctv.row + 1; i < N; i++) {
                    if (copied[i][cctv.col] == '6') {
                        break;
                    }
                    if (copied[i][cctv.col] == '0') {
                        copied[i][cctv.col] = '7';
                    }
                }
            }
            if (cctv.left) {
                for (int i = cctv.col - 1; i >= 0; i--) {
                    if (copied[cctv.row][i] == '6') {
                        break;
                    }
                    if (copied[cctv.row][i] == '0') {
                        copied[cctv.row][i] = '7';
                    }
                }
            }
            if (cctv.right) {
                for (int i = cctv.col + 1; i < M; i++) {
                    if (copied[cctv.row][i] == '6') {
                        break;
                    }
                    if (copied[cctv.row][i] == '0') {
                        copied[cctv.row][i] = '7';
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copied[i][j] == '0') {
                    count++;
                }
            }
        }

        return count;
    }

    static class CCTV {
        int row;
        int col;
        int num;
        boolean up;
        boolean down;
        boolean left;
        boolean right;

        CCTV (int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
        }

        public void changeDirection(int direction) {
            if (num == 1) {
                if (direction == 1) {
                    up = false;
                    down = false;
                    right = true;
                    left = false;
                } else if (direction == 2) {
                    up = false;
                    down = true;
                    right = false;
                    left = false;
                } else if (direction == 3) {
                    up = false;
                    down = false;
                    right = false;
                    left = true;
                } else if (direction == 4) {
                    up = true;
                    down = false;
                    right = false;
                    left = false;
                }
            } else if (num == 2) {
                if (direction == 1) {
                    up = true;
                    down = true;
                    left = false;
                    right = false;
                } else if (direction == 2) {
                    up = false;
                    down = false;
                    left = true;
                    right = true;
                }
            } else if (num == 3) {
                if (direction == 1) {
                    up = false;
                    down = true;
                    right = true;
                    left = false;
                } else if (direction == 2) {
                    up = false;
                    down = true;
                    right = false;
                    left = true;
                } else if (direction == 3) {
                    up = true;
                    down = false;
                    left = true;
                    right = false;
                } else if (direction == 4) {
                    up = true;
                    down = false;
                    right = true;
                    left = false;
                }
            } else if (num == 4) {
                if (direction == 1) {
                    up = true;
                    down = true;
                    right = true;
                    left = false;
                } else if (direction == 2) {
                    up = false;
                    down = true;
                    right = true;
                    left = true;
                } else if (direction == 3) {
                    up = true;
                    down = true;
                    right = false;
                    left = true;
                } else if (direction == 4) {
                    up = true;
                    down = false;
                    right = true;
                    left = true;
                }
            } else {
                up = true;
                down = true;
                right = true;
                left = true;
            }
        }
    }
}