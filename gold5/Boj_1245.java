package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    농장 관리
    https://www.acmicpc.net/problem/1245
*/
public class Boj_1245 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static boolean isPeak;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    isPeak = true;
                    dfs(i, j);
                    if (isPeak) count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int d = 0; d < 8; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                if (map[nr][nc] > map[r][c]) isPeak = false;
                if (!visited[nr][nc] && map[nr][nc] == map[r][c]) dfs(nr, nc);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_1245().solution();
    }
}
