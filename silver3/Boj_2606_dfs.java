package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    바이러스
    https://www.acmicpc.net/problem/2606
*/
public class Boj_2606_dfs {
    static int N, ans;
    static boolean[][] graph;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        graph = new boolean[N + 1][N + 1];
        ans = -1;
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            graph[c1][c2] = true;
            graph[c2][c1] = true;
        }
        dfs(new boolean[N + 1], 1);
        System.out.println(ans);
    }

    private static void dfs(boolean[] visited, int computer) {
        visited[computer] = true;
        ans++;
        for (int i = 0; i <= N; i++) {
            if (visited[i]) continue;
            if (!graph[computer][i]) continue;
            dfs(visited, i);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2606_dfs().solution();
    }
}
