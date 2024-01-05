package bronze5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    검증수
    https://www.acmicpc.net/problem/2475
*/
public class Boj_2475 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int number = 0;
        for (int i = 0; i < 5; i++) {
            number += Math.pow(Integer.parseInt(st.nextToken()), 2);
        }
        System.out.println(number % 10);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2475().solution();
    }
}
