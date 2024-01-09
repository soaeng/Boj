package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    상자넣기
    https://www.acmicpc.net/problem/1965
*/
public class Boj_1965 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] box = new int[N];
        int[] dp = new int[N];
        int max = 0;
        Arrays.fill(dp, 1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (box[i] > box[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {
        new Boj_1965().solution();
    }
}
