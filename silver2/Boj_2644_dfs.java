package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    촌수계산
    https://www.acmicpc.net/problem/2644
*/
public class Boj_2644_dfs {
    static int n, v, ans;
    static boolean[][] graph;
    static boolean[] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new boolean[n][n];
        visited = new boolean[n];
        ans = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken()) - 1;
        v = Integer.parseInt(st.nextToken()) - 1;
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parents = Integer.parseInt(st.nextToken()) - 1;
            int child = Integer.parseInt(st.nextToken()) - 1;
            graph[parents][child] = true;
            graph[child][parents] = true;
        }
        dfs(u, 0);
        System.out.println(ans);
    }

    private static void dfs(int u, int depth) {
        if (u == v) {
            ans = depth;
            return;
        }
        visited[u] = true;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            if (graph[u][i]) dfs(i, depth + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2644_dfs().solution();
    }
}
