package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_2445 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            if (i < N) {
                for (int j = 0; j < N; j++) {
                    if (j <= i) sb.append("*");
                    else sb.append(" ");
                }
                for (int j = 0; j < N; j++) {
                    if (j < N - i - 1) sb.append(" ");
                    else sb.append("*");
                }
            } else {
                for (int j = 0; j < N; j++) {
                    if (j + 1 >= 2 * N - i) sb.append(" ");
                    else sb.append("*");
                }
                for (int j = 0; j < N; j++) {
                    if (j > i - N) sb.append("*");
                    else sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2445().solution();
    }

}
