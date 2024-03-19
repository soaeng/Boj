package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    가장 큰 증가하는 부분 수열
    https://www.acmicpc.net/problem/11055
*/
public class Boj_11055 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(br.readLine());
            return;
        }
        int[] arr = new int[N];
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = arr[0];
        int ans = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) dp[i] = Math.max(dp[i], dp[j] + arr[i]);
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
