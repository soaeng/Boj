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
        int[] numbers = new int[9];
        for (char ch : br.readLine().toCharArray()) {
            int n = ch - '0';
            if (n == 9) numbers[6]++;
            else numbers[n]++;
        }
        numbers[6] = numbers[6] % 2 == 0 ? (numbers[6] / 2) : ((numbers[6] + 1) / 2);
        for (int i = 0; i < 9; i++) {
            set = Math.max(set, numbers[i]);
        }
        System.out.println(set);
    }

    public static void main(String[] args) throws Exception {
        new Boj_1475().solution();
    }
}
