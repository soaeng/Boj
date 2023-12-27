package bronze1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    일곱 난쟁이
    https://www.acmicpc.net/problem/2309
*/
public class Boj_2309_recursive {
    static int[] dwarf, input;
    static StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = new int[9];
        dwarf = new int[7];
        for (int i = 0; i < 9; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        comb(0, 0);
    }

    static void comb(int cnt, int start) {
        if (cnt == 7) {
            int sum = 0;
            for (int n : dwarf) sum += n;
            if (sum == 100) {
                Arrays.sort(dwarf);
                for (int n : dwarf) sb.append(n).append("\n");
                System.out.println(sb);
                System.exit(0);
            }
            return;
        }
        for (int i = start; i < 9; i++) {
            dwarf[cnt] = input[i];
            comb(cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2309_recursive().solution();
    }
}
