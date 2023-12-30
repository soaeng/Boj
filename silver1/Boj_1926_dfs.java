package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    그림
    https://www.acmicpc.net/problem/1926
*/
public class Boj_1926_dfs {
    static int n, m, area, max;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] map;
    static boolean[][] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                if (str.charAt(j * 2) == '1') map[i][j] = true;
            }
        }
        int count = 0;
        max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j]) {
                    count++;
                    area = 0;
                    visited[i][j] = true;
                    dfs(i, j);
                    max = Math.max(max, area);
                }
            }
        }
        sb.append(count).append("\n").append(max);
        System.out.println(sb);
    }

    private static void dfs(int r, int c) {
        area++;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if (visited[nr][nc] || !map[nr][nc]) continue;
            visited[nr][nc] = true;
            dfs(nr, nc);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_1926_dfs().solution();
    }
}
