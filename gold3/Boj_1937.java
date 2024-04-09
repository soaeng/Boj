package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    욕심쟁이 판다
    https://www.acmicpc.net/problem/1937
*/
public class Boj_1937 {
    static int N;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] bamboo, dp;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bamboo = new int[N][N];
        dp = new int[N][N];
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                bamboo[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (dp[r][c] != 0) continue;
                max = Math.max(max, dfs(r, c));
            }
        }
        System.out.println(max);
    }

    private static int dfs(int r, int c) {
        if (dp[r][c] != 0) return dp[r][c];
        dp[r][c] = 1;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (isOutOfRange(nr, nc)) continue;
            if (bamboo[r][c] >= bamboo[nr][nc]) continue;
            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
        }
        return dp[r][c];
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
