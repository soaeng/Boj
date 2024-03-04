package bronze1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    부녀회장이 될테야
    https://www.acmicpc.net/problem/2775
*/
public class Boj_2775 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[k + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                dp[0][i] = i;
            }
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            sb.append(dp[k][n]).append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
