package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    미세먼지 안녕!
    https://www.acmicpc.net/problem/17144
*/
public class Boj_17144_2 {
    static int R, C, T, filter;
    static int[][] grid;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        grid = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
                if (grid[r][c] == -1) filter = r;
            }
        }
        while (T-- > 0) {
            spread();
            operate();
        }
        System.out.println(calculate());
    }

    private static void printGrid() {
        for (int[] gg : grid) {
            for (int g : gg) System.out.printf("%3d", g);
            System.out.println();
        }
    }

    private static void spread() {
        int[][] temp = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                for (int d = 0; d < 4; d++) {
                    int nr = r + deltas[d][0];
                    int nc = c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (grid[nr][nc] == -1) continue;
                    temp[nr][nc] += (grid[r][c] / 5);
                    temp[r][c] -= (grid[r][c] / 5);
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == -1) continue;
                grid[r][c] += temp[r][c];
            }
        }
    }

    private static void operate() {
        // 공기청정기 위
        // 좌
        for (int r = filter - 2; r > 0; r--) {
            grid[r][0] = grid[r - 1][0];
        }
        // 상
        for (int c = 0; c < C - 1; c++) {
            grid[0][c] = grid[0][c + 1];
        }
        // 우
        for (int r = 0; r < filter; r++) {
            grid[r][C - 1] = grid[r + 1][C - 1];
        }
        // 하
        for (int c = C - 1; c > 1; c--) {
            grid[filter - 1][c] = grid[filter - 1][c - 1];
        }

        // 공기청정기 아래
        // 좌
        for (int r = filter + 1; r < R - 1; r++) {
            grid[r][0] = grid[r + 1][0];
        }
        // 하
        for (int c = 0; c < C - 1; c++) {
            grid[R - 1][c] = grid[R - 1][c + 1];
        }
        // 우
        for (int r = R - 1; r > filter; r--) {
            grid[r][C - 1] = grid[r - 1][C - 1];
        }
        // 상
        for (int c = C - 1; c > 1; c--) {
            grid[filter][c] = grid[filter][c - 1];
        }
        grid[filter - 1][1] = 0;
        grid[filter][1] = 0;
    }

    private static int calculate() {
        int amount = 2;
        for (int[] gg : grid) {
            for (int g : gg) amount += g;
        }
        return amount;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
