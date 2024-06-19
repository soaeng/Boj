package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    등산
    https://www.acmicpc.net/problem/1486
*/
public class Boj_1486 {
    static int N, M, T, D;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int INF = Integer.MAX_VALUE;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                char ch = input.charAt(c);
                map[r][c] = ch >= 'a' ? ch - 'a' + 26 : ch - 'A';
            }
        }

        int[][] dist = (int[][]) dijkstra(0, 0, true);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.weight - o1.weight);
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (dist[r][c] >= D) continue;
                pq.offer(new Node(r, c, map[r][c]));
            }
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int time = (int) dijkstra(node.r, node.c, false);
            if (time + dist[node.r][node.c] > D) continue;
            System.out.println(map[node.r][node.c]);
            break;
        }
    }

    private static Object dijkstra(int r, int c, boolean isClimb) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[r][c] = 0;
        pq.offer(new Node(r, c, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.r][node.c] < node.weight) continue;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (Math.abs(map[node.r][node.c] - map[nr][nc]) > T) continue;
                int nw = node.weight;
                if (map[nr][nc] <= map[node.r][node.c]) nw += 1;
                else nw += (int) Math.pow((map[nr][nc] - map[node.r][node.c]), 2);
                if (dist[nr][nc] > nw) {
                    dist[nr][nc] = nw;
                    pq.offer(new Node(nr, nc, dist[nr][nc]));
                }
            }
        }
        return isClimb ? dist : dist[0][0];
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node implements Comparable<Node> {
        int r, c, weight;

        public Node(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return weight - node.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
