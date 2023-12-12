package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    별 찍기 - 9        
    https://www.acmicpc.net/problem/2446
*/
public class Boj_2446 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            sb.append(" ".repeat(i));
            sb.append("*".repeat((2 * N) - (2 * i) - 1));
            sb.append("\n");
        }
        for (int i = 1; i < N; i++) {
            sb.append(" ".repeat(N - i - 1));
            sb.append("*".repeat(2 * N - ((N - i) * 2) + 1));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2446().solution();
    }
}
