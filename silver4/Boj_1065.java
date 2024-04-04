package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    한수
    https://www.acmicpc.net/problem/1065
*/
public class Boj_1065 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N < 100) {
            System.out.println(N);
            return;
        }
        int count = 99;
        for (int i = 100; i <= N; i++) {
            int h = i / 100;
            int t = (i / 10) % 10;
            int o = i % 10;
            if ((h - t) == (t - o)) count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
