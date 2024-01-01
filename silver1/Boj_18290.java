package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    NM과 K (1)
    https://www.acmicpc.net/problem/18290
*/
public class Boj_18290 {
    static int N, M, K, max, ans;
    static int[][] map;
    static boolean[][] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        ans = Integer.MIN_VALUE;
        max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
        comb(0, 0, 0, 0);
        System.out.println(ans);
    }

    private static void comb(int r, int c, int cnt, int sum) {
        if (sum + ((K - cnt) * max) < ans) return;
        if (cnt == K) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = r; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (i - 1 >= 0 && visited[i - 1][j]) continue;
                if (i + 1 < N && visited[i + 1][j]) continue;
                if (j - 1 >= 0 && visited[i][j - 1]) continue;
                if (j + 1 < M && visited[i][j + 1]) continue;
                visited[i][j] = true;
                comb(i, j, cnt + 1, sum + map[i][j]);
                visited[i][j] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_18290().solution();
    }
}
