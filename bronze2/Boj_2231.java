package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    분해합
    https://www.acmicpc.net/problem/2231
*/
public class Boj_2231 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int start = input.length() * 9;
        int N = Integer.parseInt(input);
        start = N - start;
        for (int i = start; i < N; i++) {
            int number = i;
            int sum = i;
            while (number != 0) {
                sum += number % 10;
                number /= 10;
            }
            if (sum == N) {
                System.out.println(i);
                System.exit(0);
                break;
            }
        }
        System.out.println(0);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
