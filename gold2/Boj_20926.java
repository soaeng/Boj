package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    얼음 미로
    https://www.acmicpc.net/problem/20926
*/
public class Boj_20926 {
    static int H, W;
    static char[][] map;
    static final int INF = Integer.MAX_VALUE;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        int sr = -1, sc = -1;
        for (int r = 0; r < H; r++) {
            String input = br.readLine();
            for (int c = 0; c < W; c++) {
                map[r][c] = input.charAt(c);
                if (map[r][c] == 'T') {
                    sr = r;
                    sc = c;
                    map[r][c] = '0';
                }
            }
        }
        System.out.println(dijkstra(sr, sc));
    }

    private static int dijkstra(int sr, int sc) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sr, sc));
        int[][] dist = new int[H][W];
        for (int r = 0; r < H; r++) {
            Arrays.fill(dist[r], INF);
        }

        int result = INF;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r;
                int nc = node.c;
                boolean isFailed = false;
                int nt = node.t;
                while (true) {
                    nr += deltas[d][0];
                    nc += deltas[d][1];
                    if (isOutOfRange(nr, nc)) {
                        isFailed = true;
                        break;
                    }
                    if (map[nr][nc] == 'H') {
                        isFailed = true;
                        break;
                    } else if (map[nr][nc] == 'R') {
                        break;
                    } else if (map[nr][nc] == 'E') {
                        result = Math.min(result, nt);
                    }
                    nt += map[nr][nc] - '0';
                    if (nt >= dist[nr][nc]) {
                        nt = INF;
                        break;
                    }
                }
                if (isFailed) continue;
                nr -= deltas[d][0];
                nc -= deltas[d][1];
                if (nt < dist[nr][nc]) {
                    dist[nr][nc] = nt;
                    pq.offer(new Node(nr, nc, nt));
                }
            }
        }
        return result == INF ? -1 : result;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W;
    }

    private static class Node implements Comparable<Node> {
        int r, c, t;

        public Node(int r, int c) {
            this(r, c, 0);
        }

        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

        @Override
        public int compareTo(Node node) {
            return this.t - node.t;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
