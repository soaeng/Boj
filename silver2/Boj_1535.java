package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    안녕
    https://www.acmicpc.net/problem/1535
*/
public class Boj_1535 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] L = new int[N];
        int[] J = new int[N];
        int[] dp = new int[101];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            J[i] = Integer.parseInt(st.nextToken());
        }
        for (int n = 0; n < N; n++) {
            for (int l = 100; l >= L[n]; l--) {
                if (l > L[n]) dp[l] = Math.max(dp[l], dp[l - L[n]] + J[n]);
            }
        }
        System.out.println(dp[100]);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
