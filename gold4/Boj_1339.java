package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    단어 수학
    https://www.acmicpc.net/problem/1339
*/
public class Boj_1339 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] alpha = new int[26];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                alpha[input.charAt(j) - 'A'] += (int) Math.pow(10, input.length() - j - 1);
            }
        }

        Arrays.sort(alpha);
        int number = 9;
        int ans = 0;
        for (int i = 25; i >= 0; i--) {
            if (alpha[i] == 0 || number == 0) break;
            ans += alpha[i] * number--;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
