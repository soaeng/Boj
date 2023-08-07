package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    이진수
    https://www.acmicpc.net/problem/3460
 */
public class Boj_3460 {
    static int T, n;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            String binary = Integer.toBinaryString(n);
            int length = binary.length();
            StringBuilder sb = new StringBuilder();
            for (int i = length - 1; i >= 0; i--) {
                if (binary.charAt(i) == '1') sb.append(length - i - 1).append(" ");
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_3460().solution();
    }
}
