package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    거스름돈
    https://www.acmicpc.net/problem/14916
*/
public class Boj_14916_dp {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
//        if (N < 6) {
//            if (N == 2 || N == 5) System.out.println(1);
//            else if (N == 4) System.out.println(2);
//            else System.out.println(-1);
//            return;
//        }
        int[] dp = new int[N + 1];
//        Arrays.fill(dp, 100001);
//        dp[2] = 1;
//        dp[4] = 2;
//        dp[5] = 1;
//        for (int i = 6; i <= N; i++) {
//            dp[i] = Math.min(dp[i - 2] + 1, dp[i - 5] + 1);
//        }
        int[] unit = {2, 5};
        for (int i = 1; i <= N; i++) {
            dp[i] = 100001;
            for (int n : unit) {
                if (i - n >= 0) dp[i] = Math.min(dp[i], dp[i - n] + 1);
            }
        }
        System.out.println(dp[N]);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
