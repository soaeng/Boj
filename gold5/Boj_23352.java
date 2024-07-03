package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    방탈출
    https://www.acmicpc.net/problem/23352
*/
public class Boj_23352 {
    static int N, M, max, result;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0) continue;
                bfs(r, c);
            }
        }

        System.out.println(result);
    }

    private static void bfs(int r, int c) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(new Node(r, c, 0));
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.length > max) {
                max = node.length;
                result = map[r][c] + map[node.r][node.c];
            } else if (node.length == max) {
                result = Math.max(result, map[r][c] + map[node.r][node.c]);
            }
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (map[nr][nc] == 0) continue;
                if (visited[nr][nc]) continue;
                queue.offer(new Node(nr, nc, node.length + 1));
                visited[nr][nc] = true;
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node {
        int r, c, length;

        public Node(int r, int c, int length) {
            this.r = r;
            this.c = c;
            this.length = length;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
