package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    가희의 고구마 먹방
    https://www.acmicpc.net/problem/21772
*/
public class Boj_21772 {
    static int R, C, T, ans;
    static int[][] deltas = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static char[][] map;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        ans = 0;
        map = new char[R][C];
        int gr = 0, gc = 0;
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 'G') {
                    gr = r;
                    gc = c;
                }
            }
        }
        dfs(gr, gc, 0, 0);
        System.out.println(ans);
    }

    private static void dfs(int r, int c, int t, int cnt) {
        if (t == T) {
            ans = Math.max(ans, cnt);
            return;
        }

        for (int d = 0; d < 5; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (isOutOfRange(nr, nc)) continue;
            if (map[nr][nc] == '#') continue;
            if (map[nr][nc] == 'S') {
                map[nr][nc] = '.';
                dfs(nr, nc, t + 1, cnt + 1);
                map[nr][nc] = 'S';
            } else dfs(nr, nc, t + 1, cnt);
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    public static void main(String[] args) throws Exception {
        new Boj_21772().solution();
    }
}
