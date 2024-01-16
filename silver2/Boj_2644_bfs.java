package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    촌수계산
    https://www.acmicpc.net/problem/2644
*/
public class Boj_2644_bfs {
    static int n, v;
    static boolean[][] graph;
    static boolean[] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new boolean[n][n];
        visited = new boolean[n];
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
        System.out.println(bfs(u));
    }

    private static int bfs(int u) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        queue.offer(u);
        visited[u] = true;
        int count = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (current == v) return count;
                for (int j = 0; j < n; j++) {
                    if (visited[j]) continue;
                    if (!graph[current][j]) continue;
                    queue.offer(j);
                    visited[j] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        new Boj_2644_bfs().solution();
    }
}
