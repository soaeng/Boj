package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    방 배정하기
    https://www.acmicpc.net/problem/14697
*/
public class Boj_14697 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] units = new int[3];
        for (int i = 0; i < 3; i++) {
            units[i] = Integer.parseInt(st.nextToken());
        }
        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = 301;
            for (int n : units) {
                if (i >= n) {
                    dp[i] = Math.min(dp[i], dp[i - n] + 1);
                }
            }
        }
        System.out.println(dp[N] == 301 ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
