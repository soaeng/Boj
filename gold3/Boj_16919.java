package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    봄버맨 2
    https://www.acmicpc.net/problem/16919
*/
public class Boj_16919 {
    static int R, C, N;
    static char[][] map;
    static ArrayList<Bomb> bombs;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        bombs = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'O') bombs.add(new Bomb(i, j));
            }
        }

        if (N != 1) {
            if (N % 2 == 0) {
                fillBombs();
            } else {
                fillBombs();
                explodeBombs();
                if (N % 4 == 1) {
                    bombs = findBombs();
                    fillBombs();
                    explodeBombs();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char[] mm : map) {
            sb.append(mm).append("\n");
        }
        System.out.println(sb);
    }

    private static void explodeBombs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (Bomb bomb : bombs) {
            map[bomb.r][bomb.c] = '.';
            for (int d = 0; d < 4; d++) {
                int nr = bomb.r + deltas[d][0];
                int nc = bomb.c + deltas[d][1];
                if (!isInRange(nr, nc)) continue;
                map[nr][nc] = '.';
            }
        }
    }

    private static ArrayList<Bomb> findBombs() {
        ArrayList<Bomb> bombs = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'O') bombs.add(new Bomb(i, j));
            }
        }
        return bombs;
    }

    private static void fillBombs() {
        for (int i = 0; i < R; i++) {
            Arrays.fill(map[i], 'O');
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static class Bomb {
        int r, c;

        public Bomb(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_16919().solution();
    }
}

