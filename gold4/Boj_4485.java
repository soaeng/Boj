package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    녹색 옷 입은 애가 젤다지?
    https://www.acmicpc.net/problem/4485
*/
public class Boj_4485 {
    static int N;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            map = new int[N][N];
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem ").append(tc++).append(": ").append(bfs()).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] cost = new int[N][N];
        for (int r = 0; r < N; r++) {
            Arrays.fill(cost[r], Integer.MAX_VALUE);
        }
        pq.offer(new Node(0, 0, map[0][0]));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.r == N - 1 && node.c == N - 1) return cost[N - 1][N - 1];
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (map[nr][nc] + node.rupee < cost[nr][nc]) {
                    cost[nr][nc] = map[nr][nc] + node.rupee;
                    pq.offer(new Node(nr, nc, cost[nr][nc]));
                }
            }
        }
        return cost[N - 1][N - 1];
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static class Node implements Comparable<Node> {
        int r, c, rupee;

        public Node(int r, int c, int rupee) {
            this.r = r;
            this.c = c;
            this.rupee = rupee;
        }

        @Override
        public int compareTo(Node node) {
            return this.rupee - node.rupee;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
