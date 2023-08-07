package bronze5;
/*
    별찍기 - 1
    https://www.acmicpc.net/problem/2438
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_2438 {
    static int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            sb.append("*".repeat(i + 1));
            sb.append("\n");
        }
        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    public static void main(String[] args) throws Exception {
        new Boj_2438().solution();
    }
}
