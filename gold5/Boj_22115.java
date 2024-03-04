package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    창영이와 커피
    https://www.acmicpc.net/problem/22115
*/
public class Boj_22115 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] caffeine = new int[N + 1];
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], 1, dp[i].length, 101);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            caffeine[i] = Integer.parseInt(st.nextToken());
        }
        for (int n = 1; n <= N; n++) {
            for (int k = 1; k <= K; k++) {
                if (k - caffeine[n] > 0) {
                    dp[n][k] = Math.min(dp[n - 1][k - caffeine[n]] + 1, dp[n - 1][k]);
                } else if (k - caffeine[n] == 0)
                    dp[n][k] = 1;
                else {
                    dp[n][k] = dp[n - 1][k];
                }
            }
//
//            for (int[] dd : dp) {
//                for (int d : dd) {
//                    System.out.printf("%10d  ", d);
//                }
//                System.out.println();
//            }
//            System.out.println("===================");

        }
        System.out.println(dp[N][K] == 101 ? -1 : dp[N][K]);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
