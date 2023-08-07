package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    카이사르 암호
    https://www.acmicpc.net/problem/5598
 */
public class Boj_5598 {
    static String caesar;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        caesar = br.readLine();
        int length = caesar.length();

        for (int i = 0; i < length; i++) {
            char c = caesar.charAt(i);
            if (c - 3 < 65) {
                sb.append(Character.toString(c + 23));
            } else {
                sb.append(Character.toString(c - 3));
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_5598().solution();
    }
}
