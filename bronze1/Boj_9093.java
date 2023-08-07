package bronze1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    단어 뒤집기
    https://www.acmicpc.net/problem/9093
 */
public class Boj_9093 {
    static int T;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder word = new StringBuilder();
            while (st.hasMoreTokens()) {
                word.append(st.nextToken());
                sb.append(word.reverse()).append(" ");
                word.delete(0, word.length());
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_9093().solution();
    }
}
