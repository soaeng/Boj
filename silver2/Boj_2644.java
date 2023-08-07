package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    촌수계산
    https://www.acmicpc.net/problem/2644
*/
public class Boj_2644 {
    static int n, m, a, b, ans = 0;
    static int[][] map;
    static boolean[] visit;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        visit = new boolean[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
            map[y][x] = 1;
        }
        dfs(a, 0);
        if (ans == 0) System.out.println(-1);
        else System.out.println(ans);
    }

    static void dfs(int start, int count) {
        if (start == b) {
            ans = count;
            return;
        }
        if (visit[start]) return;
        visit[start] = true;
        for (int i = 1; i <= n; i++) {
            if (!visit[i] && map[start][i] == 1) {
                dfs(i, count + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2644().solution();
    }
}
