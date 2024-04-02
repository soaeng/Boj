package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    동전교환
    https://jungol.co.kr/problem/2000
*/
public class Jo_2000 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] units = new int[N];
        for (int i = 0; i < N; i++) {
            units[i] = Integer.parseInt(st.nextToken());
        }
        int W = Integer.parseInt(br.readLine());
        int[] dp = new int[W + 1];
        for (int i = 1; i <= W; i++) {
            dp[i] = 64001;
            for (int unit : units) {
                if (i >= unit) dp[i] = Math.min(dp[i], dp[i - unit] + 1);
            }
        }
        System.out.println(dp[W]);
    }
}
