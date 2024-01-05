package bronze5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    구구단
    https://www.acmicpc.net/problem/2739
*/
public class Boj_2739 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i < 10; i++) {
            sb.append(N).append(" * ").append(i).append(" = ").append(N * i).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2739().solution();
    }
}
