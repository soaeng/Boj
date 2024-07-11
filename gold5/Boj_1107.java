package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    리모컨
    https://www.acmicpc.net/problem/1107
*/
public class Boj_1107 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 100) {
            System.out.println(0);
            return;
        }
        int min = Math.abs(N - 100);
        int M = Integer.parseInt(br.readLine());
        if (M == 0) {
            if (N > 0) {
                System.out.println(Math.min(min, (int) (Math.log10(N) + 1)));
            } else {
                System.out.println(1);
            }
            return;
        }
        boolean[] broken = new boolean[10];
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            broken[Integer.parseInt(st.nextToken())] = true;
        } // end of input

        if (!broken[0]) min = Math.min(min, N == 0 ? 1 : N + 1);
        for (int ch = 1; ch < 1000000; ch++) {
            boolean isPossible = true;
            int temp = ch;
            while (temp > 0) {
                if (broken[temp % 10]) {
                    isPossible = false;
                    break;
                }
                temp /= 10;
            }
            if (isPossible) {
                min = Math.min(min, ((int) Math.log10(ch) + 1) + Math.abs(N - ch));
            }
        }
        System.out.println(min);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
