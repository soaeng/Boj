package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    홀수
    https://www.acmicpc.net/problem/2576
 */
public class Boj_2576 {
    static int ans = 0;
    static int MIN = Integer.MAX_VALUE;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n % 2 != 0) {
                ans += n;
                if (MIN > n) MIN = n;
            }
        }
        if (ans == 0) sb.append("-1");
        else sb.append(ans).append("\n").append(MIN);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2576().solution();
    }
}
