package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    별 찍기 - 4
    https://www.acmicpc.net/problem/2441
 */
public class Boj_2441 {
    static int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            sb.append(" ".repeat(i));
            sb.append("*".repeat(N - i));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2441().solution();
    }
}
