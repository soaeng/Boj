package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    N과 M (8)
    https://www.acmicpc.net/problem/15657
*/
public class Boj_15657 {
    static int N, M;
    static int[] src, tgt;
    static StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N];
        tgt = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(src);
        comb(0, 0);
        System.out.println(sb);
    }

    static void comb(int start, int cnt) {
        if (cnt == M) {
            for (int n : tgt) sb.append(n).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = start; i < N; i++) {
            tgt[cnt] = src[i];
            comb(i, cnt + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_15657().solution();
    }
}
