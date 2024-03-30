package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    N-Queen
    https://www.acmicpc.net/problem/9663
*/
public class Boj_9663 {
    static int N, answer = 0;
    static int[] tgt;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tgt = new int[N];
        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int depth) {
        if (depth == N) {
            answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            tgt[depth] = i;
            if (isPossible(depth)) dfs(depth + 1);
        }
    }

    private static boolean isPossible(int n) {
        for (int i = 0; i < n; i++) {
            if (tgt[n] == tgt[i]) return false;
            if (Math.abs(tgt[n] - tgt[i]) == Math.abs(n - i)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
