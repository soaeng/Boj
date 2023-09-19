package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    파이프 옮기기 1
    https://www.acmicpc.net/problem/17070
*/
public class Boj_17070 {
    static int N;
    static int[][] map;
    static int[][][] dp;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[3][N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0][1] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {
                if (map[i][j] == 0) dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1];
                if (i - 1 >= 0 && map[i][j] == 0) dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j];
                if (i - 1 >= 0 && map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0)
                    dp[2][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
            }
        }
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            ans += dp[i][N - 1][N - 1];
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Boj_17070().solution();
    }
}
