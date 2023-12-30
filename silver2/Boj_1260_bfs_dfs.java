package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    DFS와 BFS
    https://www.acmicpc.net/problem/1260
*/
public class Boj_1260_bfs_dfs {
    static int N;
    static boolean[][] graph;
    static StringBuilder sb;
    static boolean[] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        graph = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1][v2] = true;
            graph[v2][v1] = true;
        }
        dfs(V);
        sb.append("\n");
        visited = new boolean[N + 1];
        bfs(V);
        System.out.println(sb);
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;
        sb.append(v).append(" ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (visited[i]) continue;
                if (!graph[current][i]) continue;
                queue.offer(i);
                visited[i] = true;
                sb.append(i).append(" ");
            }
        }
    }

    private static void dfs(int v) {
        visited[v] = true;
        sb.append(v).append(" ");
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            if (!graph[v][i]) continue;
            dfs(i);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_1260_bfs_dfs().solution();
    }
}
