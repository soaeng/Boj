package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    유기농 배추
    https://www.acmicpc.net/problem/1012
*/
public class Boj_1012_bfs {
    static int M, N, K;
    static boolean[][] map;
    static boolean[][] visited;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int count = 0;
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new boolean[N][M];
            visited = new boolean[N][M];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                map[r][c] = true;
            }
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (visited[r][c]) continue;
                    if (!map[r][c]) continue;
                    count++;
                    bfs(r, c);
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int r, int c) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(r, c));
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc]) continue;
                if (!map[nr][nc]) continue;
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = true;
            }
        }
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
