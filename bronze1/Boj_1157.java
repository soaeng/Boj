package bronze1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    단어 공부
    https://www.acmicpc.net/problem/1157
*/
public class Boj_1157 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().toUpperCase();
        int[] alphabet = new int[26];
        int length = input.length();
        int max = 0;
        int cnt = 0;
        int idx = 0;
        for (int i = 0; i < length; i++) {
            int a = alphabet[input.charAt(i) - 'A']++;
            if (max < a + 1) {
                max = a + 1;
                idx = input.charAt(i) - 'A';
            }
        }
        for (int a : alphabet) {
            if (a == max) cnt++;
        }
        System.out.println(cnt > 1 ? "?" : (char) (idx + 'A'));
    }

    public static void main(String[] args) throws Exception {
        new Boj_1157().solution();
    }
}
