package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    설탕 배달
    https://www.acmicpc.net/problem/2839
*/
public class Boj_2839 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        while (N > 0) {
            if (N % 5 == 0) {
                ans += N / 5;
                N %= 5;
            } else {
                N -= 3;
                ans++;
            }
        }
        System.out.println(N == 0 ? ans : -1);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
