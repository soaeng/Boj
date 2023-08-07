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
public class Boj_1260 {
    static int N, M, V;
    static boolean[] visit;
    static Queue<Integer> queue;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        visit = new boolean[N + 1];
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[b][a] = 1;
        }
        dfs(V);
        sb.append("\n");
        visit = new boolean[N + 1];
        bfs(V);
        System.out.println(sb);
    }

    static void dfs(int start) {
        visit[start] = true;
        sb.append(start).append(" ");
        for (int i = 1; i <= N; i++) {
            if (map[start][i] == 1 && !visit[i]) dfs(i);
        }
    }

    static void bfs(int start) {
        queue = new LinkedList<>();
        queue.offer(start);
        int n;
        while (!queue.isEmpty()) {
            n = queue.poll();
            if (visit[n]) continue;
            sb.append(n).append(" ");
            visit[n] = true;
            for (int i = 1; i <= N; i++) {
                if (!visit[i] && map[n][i] == 1) {
                    queue.offer(i);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_1260().solution();
    }
}
