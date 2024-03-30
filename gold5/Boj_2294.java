package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    동전 2
    https://www.acmicpc.net/problem/2294
*/
public class Boj_2294 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] dp = new int[K + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.fill(dp, 100001);
        dp[0] = 0;
        for (int n = 0; n < N; n++) {
            for (int k = arr[n]; k <= K; k++) {
                dp[k] = Math.min(dp[k], dp[k - arr[n]] + 1);
            }
        }
        System.out.println(dp[K] > 100000 ? -1 : dp[K]);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
