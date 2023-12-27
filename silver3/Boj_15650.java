package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    N과 M (2)
    https://www.acmicpc.net/problem/15650
*/
public class Boj_15650 {
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
        for (int i = 0; i < N; i++) {
            src[i] = i + 1;
        }
        comb(0, 0);
        System.out.println(sb);
    }

    static void comb(int cnt, int start) {
        if (cnt == M) {
            for (int n : tgt) sb.append(n).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = start; i < N; i++) {
            tgt[cnt] = src[i];
            comb(cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_15650().solution();
    }
}
