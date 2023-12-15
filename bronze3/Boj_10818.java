package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    최소, 최대
    https://www.acmicpc.net/problem/10818
 */
public class Boj_10818 {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, numbers[i]);
            max = Math.max(max, numbers[i]);
        }
        sb.append(min).append(" ").append(max);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10818().solution();
    }
}
