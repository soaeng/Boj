package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    빵집
    https://www.acmicpc.net/problem/3109
*/
public class Boj_3109 {
    static int R, C, count;
    static char[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 1}, {0, 1}, {1, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }
        for (int r = 0; r < R; r++) {
            dfs(r, 0);
        }
        System.out.println(count);
    }

    private static boolean dfs(int r, int c) {
        visited[r][c] = true;
        for (int d = 0; d < 3; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nc == C - 1) {
                count++;
                return true;
            }
            if (isOutOfRange(nr, nc)) continue;
            if (visited[nr][nc]) continue;
            if (map[nr][nc] == 'x') continue;
            if (dfs(nr, nc)) return true;
        }
        return false;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
