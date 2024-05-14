package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    늑대 사냥꾼
    https://www.acmicpc.net/problem/2917
*/
public class Boj_2917 {
    static int N, M, min, er, ec;
    static int[][] dist;
    static Queue<Node> queue;
    static final int INF = Integer.MAX_VALUE;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        min = INF;
        dist = new int[N][M];
        queue = new ArrayDeque<>();
        int sr = 0, sc = 0;
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            Arrays.fill(dist[r], INF);
            for (int c = 0; c < M; c++) {
                if (input.charAt(c) == 'V') {
                    sr = r;
                    sc = c;
                } else if (input.charAt(c) == '+') {
                    dist[r][c] = 0;
                    queue.offer(new Node(r, c, 0));
                } else if (input.charAt(c) == 'J') {
                    er = r;
                    ec = c;
                }
            }
        } // end of input
        bfs();
        dijkstra(sr, sc);
        System.out.println(min);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (dist[nr][nc] > node.d + 1) {
                    dist[nr][nc] = node.d + 1;
                    queue.offer(new Node(nr, nc, dist[nr][nc]));
                }
            }
        }
    }

    private static void dijkstra(int sr, int sc) {
        boolean[][] visited = new boolean[N][M];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sr, sc, dist[sr][sc]));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.r][node.c]) continue;
            visited[node.r][node.c] = true;
            if (node.r == er && node.c == ec) {
                min = Math.min(min, node.d);
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                pq.offer(new Node(nr, nc, Math.min(node.d, dist[nr][nc])));
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node implements Comparable<Node> {
        int r, c, d;

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Node node) {
            return node.d - this.d;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
