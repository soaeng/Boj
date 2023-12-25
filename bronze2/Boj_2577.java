package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    숫자의 개수
    https://www.acmicpc.net/problem/2577
*/
public class Boj_2577 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = 1;
        for (int i = 0; i < 3; i++) {
            result *= Integer.parseInt(br.readLine());
        }
        int[] numbers = new int[10];
        while (true) {
            if (result / 10 == 0) {
                numbers[result % 10]++;
                break;
            }
            numbers[result % 10]++;
            result /= 10;
        }
        StringBuilder sb = new StringBuilder();
        for (int n : numbers) {
            sb.append(n).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2577().solution();
    }
}
