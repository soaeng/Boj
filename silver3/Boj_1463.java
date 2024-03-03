package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    1로 만들기
    https://www.acmicpc.net/problem/1463
*/
public class Boj_1463 {
    static int[] dp;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }
        System.out.println(dp[N]);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
