package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    Happy Cow
    https://www.acmicpc.net/problem/13002
*/
public class Boj_13002 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] happiness = new int[N];
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) happiness[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            dp[i][i] = happiness[i - 1] * N;
            for (int j = i - 1; j > 0; j--) {
                int day = N - i + j;
                dp[j][i] = Math.max(dp[j + 1][i] + happiness[j - 1] * day, dp[j][i - 1] + happiness[i - 1] * day);
            }
        }
        System.out.println(dp[1][N]);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
