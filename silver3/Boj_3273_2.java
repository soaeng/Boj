package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    두 수의 합
    https://www.acmicpc.net/problem/3273
*/
public class Boj_3273_2 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        boolean[] elements = new boolean[1000001];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            elements[numbers[i]] = true;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            int num = x - numbers[i];
            if (0 <= num && num <= 1000000) {
                if (elements[num]) count++;
            }
        }
        System.out.println(count / 2);
    }

    public static void main(String[] args) throws Exception {
        new Boj_3273_2().solution();
    }
}
