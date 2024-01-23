package bronze1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    세로읽기
    https://www.acmicpc.net/problem/10798
*/
public class Boj_10798 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] words = new String[5];
        for (int i = 0; i < 5; i++) {
            words[i] = br.readLine();
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (words[j].length() < i + 1) continue;
                sb.append(words[j].charAt(i));
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10798().solution();
    }
}
