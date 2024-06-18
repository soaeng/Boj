package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    옥수수밭
    https://www.acmicpc.net/problem/30024
*/
public class Boj_30024 {

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (r == 0 || r == N - 1 || c == 0 || c == M - 1) {
                    pq.offer(new Node(r, c, map[r][c]));
                    map[r][c] = 0;
                }
            }
        }

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            Node node = pq.poll();
            sb.append(node.r + 1).append(" ").append(node.c + 1).append("\n");
            map[node.r][node.c] = 0;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (map[nr][nc] == 0) continue;
                pq.offer(new Node(nr, nc, map[nr][nc]));
                map[nr][nc] = 0;
            }
        }
        System.out.print(sb);
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
            return node.weight - this.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
