package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    시니컬한 개구리
    https://www.acmicpc.net/problem/25189
*/
public class Boj_25189 {
    static final int INF = 1000001;
    static int N, M, R, C;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][][] dist;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[2][N][M];
        for (int i = 0; i < 2; i++) {
            for (int r = 0; r < N; r++) {
                Arrays.fill(dist[i][r], INF);
            }
        }
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        if (sr == R && sc == C) {
            System.out.println(0);
            return;
        }
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs(sr, sc));
    }

    private static int bfs(int sr, int sc) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(sr, sc));
        dist[1][sr][sc] = 0;
        int time = 0;
        boolean[] row = new boolean[N];
        boolean[] column = new boolean[M];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.r == R && node.c == C) return time;
                if (!node.isJumped) {
                    // 상하
                    if (!column[node.c]) {
                        for (int r = 0; r < N; r++) {
                            if (dist[0][r][node.c] <= time) continue;
                            if (dist[1][r][node.c] != INF) continue;
                            dist[1][r][node.c] = time + 1;
                            queue.offer(new Node(r, node.c, true));
                        }
                        column[node.c] = true;
                    }
                    // 좌우
                    if (!row[node.r]) {
                        for (int c = 0; c < M; c++) {
                            if (dist[0][node.r][c] <= time) continue;
                            if (dist[1][node.r][c] != INF) continue;
                            dist[1][node.r][c] = time + 1;
                            queue.offer(new Node(node.r, c, true));
                        }
                        row[node.r] = true;
                    }
                }
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0] * map[node.r][node.c];
                    int nc = node.c + deltas[d][1] * map[node.r][node.c];
                    if (isOutOfRange(nr, nc)) continue;
                    if (dist[node.isJumped ? 1 : 0][nr][nc] != INF) continue;
                    queue.offer(new Node(nr, nc, node.isJumped));
                    dist[node.isJumped ? 1 : 0][nr][nc] = time + 1;
                }
            }
            time++;
        }
        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node {
        int r, c;
        boolean isJumped;

        public Node(int r, int c) {
            this(r, c, false);
        }

        public Node(int r, int c, boolean isJumped) {
            this.r = r;
            this.c = c;
            this.isJumped = isJumped;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + "," + isJumped + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
