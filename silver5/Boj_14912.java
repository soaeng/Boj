package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    숫자 빈도수
    https://www.acmicpc.net/problem/14912
*/
public class Boj_14912 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int number = Integer.parseInt(st.nextToken());
        int count = 0;
        for (int i = 1; i <= N; i++) {
            int temp = i;
            while (temp > 0) {
                if (temp % 10 == number) count++;
                temp /= 10;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
