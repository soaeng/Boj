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
public class Boj_2206_2 {
    static int N, M;
    static boolean[][] map;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = input.charAt(c) == '1';
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][M];
        queue.offer(new Node(0, 0));
        visited[0][0][0] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            time++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.r == N - 1 && node.c == M - 1) return time;
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[node.isBroken ? 1 : 0][nr][nc]) continue;
                    if (map[nr][nc]) {
                        if (node.isBroken) continue;
                        if (visited[1][nr][nc]) continue;
                        queue.offer(new Node(nr, nc, true));
                        visited[1][nr][nc] = true;
                    } else {
                        if (visited[node.isBroken ? 1 : 0][nr][nc]) continue;
                        queue.offer(new Node(nr, nc, node.isBroken));
                        visited[node.isBroken ? 1 : 0][nr][nc] = true;
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node {
        int r, c;
        boolean isBroken;

        public Node(int r, int c) {
            this(r, c, false);
        }

        public Node(int r, int c, boolean isBroken) {
            this.r = r;
            this.c = c;
            this.isBroken = isBroken;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
