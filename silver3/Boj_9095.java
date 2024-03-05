package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    1, 2, 3 더하기
    https://www.acmicpc.net/problem/9095
*/
public class Boj_9095 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if (n < 4) {
                switch (n) {
                    case 1:
                        sb.append(1);
                        break;
                    case 2:
                        sb.append(2);
                        break;
                    case 3:
                        sb.append(4);
                        break;
                }
                sb.append("\n");
                continue;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            /*
            dp[1] (1)
            dp[2] (1, 1) (2)
            dp[3] (1, 1, 1), (1, 2), (2, 1), (3)
            dp[4] (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (2, 1, 1) (2, 2)
                  (1, 3), (3, 1)
             */
            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
            sb.append(dp[n]).append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
