package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    막대기
    https://www.acmicpc.net/problem/1094
*/
public class Boj_1094 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int ans = 0;
        for (int i = 0; i < 7; i++) {
            if ((X & (1 << i)) > 0) ans++;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
