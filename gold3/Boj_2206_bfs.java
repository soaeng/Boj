package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    벽 부수고 이동하기
    https://www.acmicpc.net/problem/2206
*/
public class Boj_2206_bfs {
    static int N, M;
    static boolean[][] wall;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wall = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == '1') wall[i][j] = true;
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][][] visited = new boolean[2][N][M];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.r == N - 1 && node.c == M - 1) return node.t;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc) || visited[node.broken ? 1 : 0][nr][nc]) continue;
                if (wall[nr][nc]) {
                    if (node.broken) continue;
                    if (visited[1][nr][nc]) continue;
                    queue.offer(new Node(nr, nc, node.t + 1, true));
                    visited[1][nr][nc] = true;
                } else {
                    if (visited[node.broken ? 1 : 0][nr][nc]) continue;
                    queue.offer(new Node(nr, nc, node.t + 1, node.broken));
                    visited[node.broken ? 1 : 0][nr][nc] = true;
                }
            }
        }
        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node {
        int r, c, t;
        boolean broken;

        public Node(int r, int c) {
            this(r, c, 1, false);
        }

        public Node(int r, int c, int t, boolean broken) {
            this.r = r;
            this.c = c;
            this.t = t;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2206_bfs().solution();
    }
}
