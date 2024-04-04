package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    영화감독 숌
    https://www.acmicpc.net/problem/1436
*/
public class Boj_1436 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int number = 666;
        while (N > 0) {
            if (String.valueOf(number).contains("666")) N--;
            number++;
        }
        System.out.println(--number);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
