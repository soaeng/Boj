package bronze5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    A/B
    https://www.acmicpc.net/problem/1008
*/
public class Boj_1008 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double A = Integer.parseInt(st.nextToken());
        double B = Integer.parseInt(st.nextToken());
        System.out.println(A / B);
    }

    public static void main(String[] args) throws Exception {
        new Boj_1008().solution();
    }
}
