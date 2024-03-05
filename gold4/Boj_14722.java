package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    우유 도시
    https://www.acmicpc.net/problem/14722
*/
public class Boj_14722 {
    static int[][] map;
    static int[][][] dp;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[3][N][N];
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // 딸 초 바 딸
        if (map[0][0] == 0) dp[0][0][0] = 1;

        for (int r = 1; r < N; r++) {
            dp[0][r][0] = (map[r][0] == 0 ? dp[2][r - 1][0] + 1 : dp[0][r - 1][0]);
            dp[1][r][0] = (map[r][0] == 1 && dp[2][r][0] < dp[0][r][0] ? dp[0][r - 1][0] + 1 : dp[1][r - 1][0]);
            dp[2][r][0] = (map[r][0] == 2 && dp[0][r][0] < dp[1][r][0] ? dp[1][r - 1][0] + 1 : dp[2][r - 1][0]);
        }

        for (int c = 1; c < N; c++) {
            dp[0][0][c] = (map[0][c] == 0 ? dp[2][0][c - 1] + 1 : dp[0][0][c - 1]);
            dp[1][0][c] = (map[0][c] == 1 && dp[2][0][c] < dp[0][0][c] ? dp[0][0][c - 1] + 1 : dp[1][0][c - 1]);
            dp[2][0][c] = (map[0][c] == 2 && dp[0][0][c] < dp[1][0][c] ? dp[1][0][c - 1] + 1 : dp[2][0][c - 1]);
        }

        for (int r = 1; r < N; r++) {
            for (int c = 1; c < N; c++) {
                dp[0][r][c] = map[r][c] == 0 ? Math.max(dp[2][r - 1][c] + 1, dp[2][r][c - 1] + 1) : Math.max(dp[0][r - 1][c], dp[0][r][c - 1]);
                dp[1][r][c] = map[r][c] == 1 && dp[2][r][c] < dp[0][r][c] ? Math.max(dp[0][r - 1][c] + 1, dp[0][r][c - 1] + 1) : Math.max(dp[1][r - 1][c], dp[1][r][c - 1]);
                dp[2][r][c] = map[r][c] == 2 && dp[0][r][c] < dp[1][r][c] ? Math.max(dp[1][r - 1][c] + 1, dp[1][r][c - 1] + 1) : Math.max(dp[2][r - 1][c], dp[2][r][c - 1]);
            }
        }
        N--;
        System.out.println(Math.max(dp[0][N][N], Math.max(dp[1][N][N], dp[2][N][N])));
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
