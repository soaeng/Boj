package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    적록색약
    https://www.acmicpc.net/problem/10026
*/
public class Boj_10026_dfs {
    static int N;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static char[][] grid;
    static boolean[][] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        grid = new char[N][N];
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < 2; i++) {
            int count = 0;
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (visited[r][c]) continue;
                    dfs(r, c, i == 0);
                    count++;
                }
            }
            sb.append(count).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(int r, int c, boolean type) {
        if (visited[r][c]) return;
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (visited[nr][nc]) continue;
            if (type) {
                if (grid[r][c] != grid[nr][nc]) continue;
            } else {
                switch (grid[r][c]) {
                    case 'R':
                    case 'G':
                        if (grid[nr][nc] == 'B') continue;
                        break;
                    case 'B':
                        if (grid[nr][nc] != 'B') continue;
                        break;
                }
            }
            dfs(nr, nc, type);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_10026_dfs().solution();
    }
}
