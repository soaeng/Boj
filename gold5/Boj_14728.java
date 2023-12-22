package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    벼락치기
    https://www.acmicpc.net/problem/14728
*/
public class Boj_14728 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] dp = new int[T + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            for (int j = T; j >= K; j--) {
                dp[j] = Math.max(dp[j], dp[j - K] + S);
            }
        }
        System.out.println(dp[T]);
    }

    public static void main(String[] args) throws Exception {
        new Boj_14728().solution();
    }
}
