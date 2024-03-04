package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    설탕 배달
    https://www.acmicpc.net/problem/2839
*/
public class Boj_2839_dp {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N < 6) {
            System.out.println((N == 3 || N == 5) ? 1 : -1);
            return;
        }
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 5001);
        dp[3] = dp[5] = 1;
        for (int i = 6; i <= N; i++) {
            dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
        }
        System.out.println(dp[N] > 5000 ? -1 : dp[N]);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
