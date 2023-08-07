package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    별 찍기 - 6
    https://www.acmicpc.net/problem/2443
 */
public class Boj_2443 {
    static int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = N; i > 0; i--) {
            sb.append(" ".repeat(N - i));
            sb.append("*".repeat(2 * i - 1));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2443().solution();
    }
}
