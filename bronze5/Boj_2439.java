package bronze5;
/*
    별찍기 - 2
    https://www.acmicpc.net/problem/2439
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_2439 {
    static int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(" ".repeat(N - i - 1));
            sb.append("*".repeat(i + 1));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2439().solution();
    }
}
