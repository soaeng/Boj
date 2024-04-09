package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    앱
    https://www.acmicpc.net/problem/7579
*/
public class Boj_7579 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memory = new int[N];
        int[] cost = new int[N];
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
            sum += cost[i];
        }
        if (sum == 0) {
            System.out.println(0);
            return;
        }
        int[] dp = new int[sum + 1];
        for (int n = 0; n < N; n++) {
            for (int m = sum; m >= cost[n]; m--) {
                dp[m] = Math.max(dp[m], dp[m - cost[n]] + memory[n]);
            }
        }
        for (int i = 0; i <= sum; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
