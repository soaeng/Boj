package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    목장 건설하기
    https://www.acmicpc.net/problem/14925
*/
public class Boj_14925 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] dp = new int[M][N];
        for (int r = 0; r < M; r++) {
            Arrays.fill(dp[r], 1);
        }
        for (int r = 0; r < M; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                if (Integer.parseInt(st.nextToken()) > 0) dp[r][c] = 0;
            }
        }
        int ans = 0;
        if (dp[0][0] == 1) ans = 1;
        for (int r = 1; r < M; r++) {
            for (int c = 1; c < N; c++) {
                if (dp[r][c] == 0) continue;
                dp[r][c] = Math.min(dp[r - 1][c - 1], Math.min(dp[r - 1][c], dp[r][c - 1])) + 1;
                ans = Math.max(ans, dp[r][c]);
            }
        }

//        for (int[] nn : dp) {
//            for (int n : nn) {
//                System.out.printf("%2d  ", n);
//            }
//            System.out.println();
//        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
