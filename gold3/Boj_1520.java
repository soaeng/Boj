package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    내리막 길
    https://www.acmicpc.net/problem/1520
*/
public class Boj_1520 {
    static int M, N;
    static int[][] map, dp, deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for (int r = 0; r < M; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[r], -1);
        }
        dfs(0, 0);
        System.out.println(dp[0][0]);
    }

    private static int dfs(int r, int c) {
        if (r == M - 1 && c == N - 1) return dp[r][c] = 1;
        if (dp[r][c] != -1) return dp[r][c];
        dp[r][c] = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (isOutOfRange(nr, nc)) continue;
            if (map[r][c] <= map[nr][nc]) continue;
            dp[r][c] += dfs(nr, nc);
        }
        return dp[r][c];
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= M || c < 0 || c >= N;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
