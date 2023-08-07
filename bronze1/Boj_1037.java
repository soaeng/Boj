package bronze1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    약수
    https://www.acmicpc.net/problem/1037
 */
public class Boj_1037 {
    static int N;
    static int[] divisor;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        divisor = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            divisor[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(divisor);
        if (N % 2 == 0) {
            N = divisor[N / 2] * divisor[N / 2 - 1];
        } else {
            N = (int) Math.pow(divisor[N / 2], 2);
        }
        System.out.println(N);
    }

    public static void main(String[] args) throws Exception {
        new Boj_1037().solution();
    }
}
