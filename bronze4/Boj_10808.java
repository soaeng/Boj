package bronze4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    알파벳 개수
    https://www.acmicpc.net/problem/10808
*/
public class Boj_10808 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int length = S.length();
        int[] alphabet = new int['z' - 'a' + 1];
        for (int i = 0; i < length; i++) {
            alphabet[S.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int n : alphabet) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10808().solution();
    }
}
