package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    단어의 개수
    https://www.acmicpc.net/problem/1152
*/
public class Boj_1152 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        System.out.println(st.countTokens());
    }

    public static void main(String[] args) throws Exception {
        new Boj_1152().solution();
    }
}
