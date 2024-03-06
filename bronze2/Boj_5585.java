package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    거스름돈
    https://www.acmicpc.net/problem/5585
*/
public class Boj_5585 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 1000 - Integer.parseInt(br.readLine());
        int[] unit = {500, 100, 50, 10, 5, 1};
        int ans = 0;
        for (int i = 0; i < 6; i++) {
            if (N >= unit[i]) {
                ans += N / unit[i];
                N %= unit[i];
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
