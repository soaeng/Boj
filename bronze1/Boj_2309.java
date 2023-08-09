package bronze1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    일곱 난쟁이
    https://www.acmicpc.net/problem/2309
*/
public class Boj_2309 {
    static int[] dwarf = new int[9];
    static int[] fake = new int[2];
    static StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = 0;
        for (int i = 0; i < 9; i++) {
            dwarf[i] = Integer.parseInt(br.readLine());
            total += dwarf[i];
        }
        combination(0, 0, total);
    }

    static void combination(int start, int count, int height) {
        if (count == 2) {
            if (height == 100) {
                Arrays.sort(dwarf);
                for (int d : dwarf) {
                    if (d == fake[0] || d == fake[1]) continue;
                    sb.append(d).append("\n");
                }
                System.out.println(sb);
                System.exit(0);
            }
            return;
        }
        for (int i = start; i < 9; i++) {
            fake[count] = dwarf[i];
            combination(i + 1, count + 1, height - fake[count]);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2309().solution();
    }
}
