package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    바이러스
    https://www.acmicpc.net/problem/2606
*/
public class Boj_2606_bfs {
    static int N;
    static boolean[][] graph;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        graph = new boolean[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            graph[c1][c2] = true;
            graph[c2][c1] = true;
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.offer(1);
        visited[1] = true;
        int ans = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (visited[i]) continue;
                if (graph[current][i]) {
                    queue.offer(i);
                    visited[i] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        new Boj_2606_bfs().solution();
    }
}
