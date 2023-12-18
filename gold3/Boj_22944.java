package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    죽음의 비
    https://www.acmicpc.net/problem/22944
*/
public class Boj_22944 {
    static int N, D, ans;
    static char[][] map;
    static int[][] hp;
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        hp = new int[N][N];
        queue = new LinkedList<>();
        ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'S') {
                    queue.offer(new Node(i, j, H));
                    hp[i][j] = H;
                }
            }
        }
        bfs();
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                int nh = node.h;
                int nd = node.d;
                if (isOutOfRange(nr, nc)) continue;
                if (node.d > 0) nd--;
                else nh--;
                if (map[nr][nc] == 'U') nd = D;
                if (map[nr][nc] == 'E') {
                    ans = Math.min(ans, node.m + 1);
                    continue;
                }
                if (hp[nr][nc] < nh + nd) {
                    queue.offer(new Node(nr, nc, nh, nd, node.m + 1));
                    hp[nr][nc] = nh + nd;
                }
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    static class Node {
        int r, c, h, d, m;
        // h: 체력, d: 우산내구도, m: 이동횟수, u: 우산유무

        public Node(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.d = 0;
            this.m = 0;
        }

        public Node(int r, int c, int h, int d, int m) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.d = d;
            this.m = m;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", h=" + h +
                    ", d=" + d +
                    ", m=" + m +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_22944().solution();
    }
}
