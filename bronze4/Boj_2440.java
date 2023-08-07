package bronze4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    별찍기 - 3
    https://www.acmicpc.net/problem/2440
 */
public class Boj_2440 {
    static int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = N; i > 0; i--) {
            sb.append("*".repeat(i));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2440().solution();
    }
}
