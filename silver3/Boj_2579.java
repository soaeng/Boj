package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    계단 오르기
    https://www.acmicpc.net/problem/2579
*/
public class Boj_2579 {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }
        stairs[0] = 0;
        dp[0] = 0;
        dp[1] = stairs[1];
        if (N == 1) {
            System.out.println(dp[1]);
            return;
        }
        dp[2] = stairs[1] + stairs[2];
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2] + stairs[i], dp[i - 3] + stairs[i - 1] + stairs[i]);
        }
        System.out.println(dp[N]);
    }


    public static void main(String[] args) throws Exception {
        new Boj_2579().solution();
    }
}
