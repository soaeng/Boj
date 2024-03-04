package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    간식 파티
    https://www.acmicpc.net/problem/20162
*/
public class Boj_20162 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] review = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            review[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[N + 1];
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = review[i];
            for (int j = 0; j < i; j++) {
                if (review[i] > review[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + review[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
