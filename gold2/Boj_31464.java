package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    초콜릿 괴도 코코 (Sweet)
    https://www.acmicpc.net/problem/31464
*/
public class Boj_31464 {
    static int N, count, originCnt;
    static boolean isCycle;
    static boolean[][] map, visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        originCnt = 0;
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < N; c++) {
                if (input.charAt(c) == '#') {
                    map[r][c] = true;
                    originCnt++;
                }
            }
        }
        int ans = 0;
        sb.append("\n");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!map[r][c]) continue;
                map[r][c] = false;
                if (validate()) {
                    ans++;
                    sb.append(r + 1).append(" ").append(c + 1).append("\n");
                }
                map[r][c] = true;
            }
        }
        sb.insert(0, ans);
        System.out.print(sb);
    }

    private static boolean validate() {
        isCycle = false;
        count = 1;
        visited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!map[r][c]) continue;
                dfs(r, c, r, c);
                return count == originCnt && !isCycle;
            }
        }
        return false;
    }

    private static void dfs(int r, int c, int pr, int pc) {
        if (isCycle) return;
        count++;
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr == pr && nc == pc) continue;
            if (isOutOfRange(nr, nc)) continue;
            if (!map[nr][nc]) continue;
            if (visited[nr][nc]) isCycle = true;
            else dfs(nr, nc, r, c);
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
