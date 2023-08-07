package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    바이러스
    https://www.acmicpc.net/problem/2606
*/
public class Boj_2606 {
    static int C, N, ans = -1;
    static int[][] map;
    static boolean[] visit;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        C = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        map = new int[C + 1][C + 1];
        visit = new boolean[C + 1];
        int right, left;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            right = Integer.parseInt(st.nextToken());
            left = Integer.parseInt(st.nextToken());
            map[right][left] = 1;
            map[left][right] = 1;
        }
        dfs(1);
        System.out.println(ans);
    }

    private static void dfs(int start) {
        visit[start] = true;
        ans++;
        for (int i = 1; i <= C; i++) {
            if (map[start][i] == 1 && !visit[i]) {
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2606().solution();
    }
}
