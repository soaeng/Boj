package bronze1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    일곱 난쟁이
    https://www.acmicpc.net/problem/2309
*/
public class Boj_2309_loop {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] input = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            input[i] = Integer.parseInt(br.readLine());
            sum += input[i];
        }
        Arrays.sort(input);
        loop:
        for (int i = 0; i < 8; i++) {
            for (int j = 1; j < 9; j++) {
                if (sum - input[i] - input[j] == 100) {
                    for (int k = 0; k < 9; k++) {
                        if (k == i || k == j) continue;
                        sb.append(input[k]).append("\n");
                    }
                    break loop;
                }
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2309_loop().solution();
    }
}
