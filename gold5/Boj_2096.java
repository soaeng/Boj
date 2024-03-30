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
        int[] dp = new int[3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.arraycopy(arr[0], 0, dp, 0, 3);
        for (int i = 1; i < N; i++) {
            int[] temp = {dp[0], dp[1], dp[2]};
            dp[0] = Math.max(temp[0], temp[1]) + arr[i][0];
            dp[1] = Math.max(temp[0], Math.max(temp[1], temp[2])) + arr[i][1];
            dp[2] = Math.max(temp[1], temp[2]) + arr[i][2];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Math.max(dp[0], Math.max(dp[1], dp[2]))).append(" ");
        System.arraycopy(arr[0], 0, dp, 0, 3);
        for (int i = 1; i < N; i++) {
            int[] temp = {dp[0], dp[1], dp[2]};
            dp[0] = Math.min(temp[0], temp[1]) + arr[i][0];
            dp[1] = Math.min(temp[0], Math.min(temp[1], temp[2])) + arr[i][1];
            dp[2] = Math.min(temp[1], temp[2]) + arr[i][2];
        }
        sb.append(Math.min(dp[0], Math.min(dp[1], dp[2]))).append(" ");
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
