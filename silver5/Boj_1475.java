package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    방 번호
    https://www.acmicpc.net/problem/1475
*/
public class Boj_1475 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int set = 0;
        String N = br.readLine();
        int[] numbers = new int[10];
        for (int i = 0; i < N.length(); i++) {
            if (N.charAt(i) - '0' == 6) numbers[9]++;
            else numbers[N.charAt(i) - '0']++;
        }
        for (int i = 0; i < 9; i++) {
            set = Math.max(set, numbers[i]);
        }
        if (numbers[9] % 2 != 0) numbers[9]++;
        System.out.println(Math.max(set, numbers[9] / 2));
    }

    public static void main(String[] args) throws Exception {
        new Boj_1475().solution();
    }
}
