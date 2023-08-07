package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    별 찍기 - 7
    https://www.acmicpc.net/problem/2444
 */
public class Boj_2444 {
    static int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i < 2 * N; i++) {
            if (i <= N) {
                sb.append(" ".repeat(N - i));
                sb.append("*".repeat(2 * i - 1));
            } else {
                sb.append(" ".repeat(i - N));
                sb.append("*".repeat((2 * N - 1) - (i - N) * 2));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2444().solution();
    }
}
