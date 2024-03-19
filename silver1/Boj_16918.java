package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_16918 {

    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int R, C, N, time;
    static char[][] map;
    static int[][] bombs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        bombs = new int[R][C];
        time = 0;
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'O') bombs[i][j] = 3;
            }
        }
        while (time++ < N) {
            if (time % 2 == 0) plantBomb();
            else if (time % 2 == 1) explodeBomb();
        }

        StringBuilder sb = new StringBuilder();
        for (char[] mm : map) {
            sb.append(mm).append("\n");
        }
        System.out.println(sb);
    }

    private static void plantBomb() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 'O') continue;
                map[r][c] = 'O';
                bombs[r][c] = time + 3;
            }
        }
    }

    private static void explodeBomb() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (bombs[r][c] != time) continue;
                map[r][c] = '.';
                for (int d = 0; d < 4; d++) {
                    int nr = r + deltas[d][0];
                    int nc = c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (map[nr][nc] != 'O') continue;
                    if (bombs[nr][nc] == time) continue;
                    map[nr][nc] = '.';
                    bombs[nr][nc] = 0;
                }
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

}
