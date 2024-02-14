package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    다리 놓기
    https://www.acmicpc.net/problem/1010
*/
public class Boj_1010 {
    static int N, M, count;
    static int[] tgt;
    static int[][] dp;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        dp = new int[30][30];
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            tgt = new int[N];
            count = 0;
//            dfs(0, 0);
//            System.out.println(count);
            sb.append(combination(M, N)).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int depth, int start) {
        if (depth == N) {
            count++;
            return;
        }

        for (int i = start; i < M; i++) {
            dfs(depth + 1, i + 1);
        }
    }

    private static int combination(int n, int r) {
        if (dp[n][r] > 0) return dp[n][r];
        if (n == r || r == 0) return dp[n][r] = 1;
        return dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
