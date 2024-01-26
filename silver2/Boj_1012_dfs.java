package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    유기농 배추
    https://www.acmicpc.net/problem/1012
*/
public class Boj_1012_dfs {
    static int M, N, K;
    static boolean[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new boolean[N][M];
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                map[r][c] = true;
            }
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (!map[r][c]) continue;
                    count++;
                    dfs(r, c);
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int r, int c) {
        map[r][c] = false;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if (!map[nr][nc]) continue;
            dfs(nr, nc);
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
