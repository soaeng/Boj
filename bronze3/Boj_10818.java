package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    최소, 최대
    https://www.acmicpc.net/problem/10818
 */
public class Boj_10818 {
    static int N, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (min > n) min = n;
            if (max < n) max = n;
        }
        sb.append(min).append(" ").append(max);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10818().solution();
    }
}
