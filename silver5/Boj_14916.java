package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    거스름돈
    https://www.acmicpc.net/problem/14916
*/
public class Boj_14916 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        while (N > 0) {
            if (N % 5 == 0) {
                ans += N / 5;
                N = 0;
            } else {
                ans++;
                N -= 2;
            }
        }
        System.out.println(N == 0 ? ans : -1);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
