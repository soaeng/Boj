package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    전자레인지
    https://www.acmicpc.net/problem/10162
*/
public class Boj_10162 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] button = {300, 60, 10};
        int[] ans = new int[3];
        for (int i = 0; i < 3; i++) {
            if (T >= button[i]) {
                ans[i] = T / button[i];
                T %= button[i];
            }
        }
        if (T != 0) System.out.println(-1);
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(ans[i]).append(" ");
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
