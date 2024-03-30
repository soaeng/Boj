package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    동전 1
    https://www.acmicpc.net/problem/2293
*/
public class Boj_2293 {
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
        dp[0] = 1;
        for (int n = 0; n < N; n++) {
            for (int k = arr[n]; k <= K; k++) {
                dp[k] += dp[k - arr[n]];
            }
        }
        System.out.println(dp[K]);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
