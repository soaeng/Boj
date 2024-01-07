package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    벽 부수고 이동하기 3
    https://www.acmicpc.net/problem/16933
*/
public class Boj_16933 {
    static int N, M, K;
    static boolean[][] wall;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
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
        boolean[][][] visited = new boolean[K + 1][N][M];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.r == N - 1 && node.c == M - 1) return node.t;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (wall[nr][nc]) {
                    if (node.k >= K || visited[node.k + 1][nr][nc]) continue;
                    if (node.t % 2 != 0) {
                        queue.offer(new Node(nr, nc, node.k + 1, node.t + 1));
                        visited[node.k + 1][nr][nc] = true;
                    } else queue.offer(new Node(node.r, node.c, node.k, node.t + 1));
                } else {
                    if (visited[node.k][nr][nc]) continue;
                    queue.offer(new Node(nr, nc, node.k, node.t + 1));
                    visited[node.k][nr][nc] = true;
                }
            }
        }
        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }

    private static class Node {
        int r, c, t, k;

        public Node(int r, int c) {
            this(r, c, 0, 1);
        }

        public Node(int r, int c, int k, int t) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.t = t;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + k + ", " + t + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_16933().solution();
    }
}
