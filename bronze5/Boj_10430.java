package bronze5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    나머지
    https://www.acmicpc.net/problem/10430
*/
public class Boj_10430 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        sb.append((a + b) % c).append("\n");
        sb.append(((a % c) + (b % c)) % c).append("\n");
        sb.append((a * b) % c).append("\n");
        sb.append(((a % c) * (b % c) % c));
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10430().solution();
    }
}
