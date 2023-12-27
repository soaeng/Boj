package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    N과 M (1)
    https://www.acmicpc.net/problem/15649
*/
public class Boj_15649 {
    static int N, M;
    static int[] src, tgt;
    static boolean[] selected;
    static StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N];
        tgt = new int[M];
        selected = new boolean[N];
        for (int i = 0; i < N; i++) {
            src[i] = i + 1;
        }
        perm(0);
        System.out.println(sb);
    }

    static void perm(int cnt) {
        if (cnt == M) {
            for (int n : tgt) sb.append(n).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if (selected[i]) continue;
            tgt[cnt] = src[i];
            selected[i] = true;
            perm(cnt + 1);
            selected[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_15649().solution();
    }
}
