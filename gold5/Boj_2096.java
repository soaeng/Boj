package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    내려가기
    https://www.acmicpc.net/problem/2096
*/
public class Boj_2096 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        int[][] dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.arraycopy(arr[0], 0, dp[0], 0, 3);
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + arr[i][0];
            dp[i][1] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2])) + arr[i][1];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]) + arr[i][2];
        }
        StringBuilder sb = new StringBuilder();
        N--;
        sb.append(Math.max(dp[N][0], Math.max(dp[N][1], dp[N][2]))).append(" ");
        N++;
        dp = new int[N][3];
        System.arraycopy(arr[0], 0, dp[0], 0, 3);
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + arr[i][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][2];
        }
        N--;
        sb.append(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
