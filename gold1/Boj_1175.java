package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    배달
    https://www.acmicpc.net/problem/1175
*/
public class Boj_1175 {
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;
    static Node[] spots;
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        // visited[방향][C][N][M];
        visited = new boolean[4][4][N][M];
        spots = new Node[2];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                // 지금 민식이가 있는 곳
                switch (map[i][j]) {
                    case 'S':
                        queue.offer(new Node(i, j));
                        for (int k = 0; k < 4; k++) visited[k][0][i][j] = true;
                        break;
                    case 'C':
                        if (spots[0] == null) spots[0] = new Node(i, j);
                        else spots[1] = new Node(i, j);
                        break;
                }
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.status == 3) return node.t;
            for (int d = 0; d < 4; d++) {
                if (node.d == d) continue;
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (!isInRange(nr, nc)) continue;
                if (visited[d][node.status][nr][nc]) continue;
                if (map[nr][nc] == '#') continue;
                if (map[nr][nc] == 'C') {
                    if (new Node(nr, nc).equals(spots[0]) && node.status != 1) {
                        queue.offer(new Node(nr, nc, d, node.t + 1, node.status + 1));
                        visited[d][node.status + 1][nr][nc] = true;
                    } else if (new Node(nr, nc).equals(spots[1]) && node.status != 2) {
                        queue.offer(new Node(nr, nc, d, node.t + 1, node.status + 2));
                        visited[d][node.status + 2][nr][nc] = true;
                    }
                } else {
                    queue.offer(new Node(nr, nc, d, node.t + 1, node.status));
                    visited[d][node.status][nr][nc] = true;
                }
            }
        }
        return -1;
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Node {
        int r, c, d, t, status;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
            this.d = -1;
            this.t = 0;
            this.status = 0;
        }

        public Node(int r, int c, int d, int t, int status) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.t = t;
            this.status = status;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) return false;
            Node node = (Node) obj;
            return Objects.equals(this.r, node.r) && this.c == node.c;
        }

        @Override
        public String toString() {
            return "(r: " + r + ", c: " + c + ", d: " + d + ", t: " + t + ", status: " + status + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_1175().solution();
    }
}
