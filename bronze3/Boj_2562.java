package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    최댓값
    https://www.acmicpc.net/problem/2562
 */
public class Boj_2562 {
    static int[] numbers = new int[9];
    static int MAX = Integer.MIN_VALUE;
    static int ans;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            if (numbers[i] > MAX) {
                MAX = numbers[i];
                ans = i + 1;
            }
        }
        sb.append(MAX).append("\n").append(ans);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2562().solution();
    }
}
