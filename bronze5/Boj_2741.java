package bronze5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    N 찍기
    https://www.acmicpc.net/problem/2741
*/
public class Boj_2741 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2741().solution();
    }
}
