package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    블랙잭
    https://www.acmicpc.net/problem/1798
*/
public class Boj_2798 {
    static int N, M, ans;
    static int[] src;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }
        comb(0, 0, 0);
        System.out.println(ans);
    }

    private static void comb(int depth, int start, int sum) {
        if (sum > M) return;
        if (depth == 3) {
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = start; i < N; i++) {
            comb(depth + 1, i + 1, sum + src[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
