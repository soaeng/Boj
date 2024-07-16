package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    김밥천국의 계단
    https://www.acmicpc.net/problem/28069
*/
public class Boj_28069_dp {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 1_000_001);
        dp[0] = 0;
        for (int n = 0; n < N; n++) {
            int nn = n + 1;
            if (nn <= N) dp[nn] = Math.min(dp[nn], dp[n] + 1);
            nn = n + n / 2;
            if (nn <= N) dp[nn] = Math.min(dp[nn], dp[n] + 1);
        }
        System.out.println(dp[N] <= K ? "minigimbob" : "water");
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
