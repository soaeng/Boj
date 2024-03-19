package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    그룹 단어 체커
    https://www.acmicpc.net/problem/1316
*/
public class Boj_1316 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        while (N-- > 0) {
            if (check(br.readLine())) answer++;
        }
        System.out.println(answer);
    }

    private static boolean check(String word) {
        boolean[] alpha = new boolean[26];
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (alpha[ch - 'a']) return false;
            alpha[ch - 'a'] = true;
            for (int j = i + 1; j < word.length(); j++) {
                if (word.charAt(j) == ch) i++;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}