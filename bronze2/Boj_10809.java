package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    알파벳 찾기
    https://www.acmicpc.net/problem/10809
 */
public class Boj_10809 {
    static String S;
    static int[] alphabet = new int[26];

    public void solution() throws Exception {
        for (int i = 0; i < 26; i++) {
            alphabet[i] = -1;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        int length = S.length();
        for (int i = 0; i < length; i++) {
            if (alphabet[S.charAt(i) - 'a'] == -1)
                alphabet[S.charAt(i) - 'a'] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int a : alphabet) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10809().solution();
    }
}
