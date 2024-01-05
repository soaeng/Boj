package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
    적록색약
    https://www.acmicpc.net/problem/10026
*/
public class Boj_10026_bfs {
    static int N;
    static char[][] grid;
    static boolean[][] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        grid = new char[N][N];
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < 2; i++) {
            int count = 0;
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (visited[r][c]) continue;
                    bfs(r, c, i == 0);
                    count++;
                }
            }
            sb.append(count).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs(int r, int c, boolean type) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(r, c, grid[r][c]));
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                if (type) {
                    if (node.color != grid[nr][nc]) continue;
                } else {
                    switch (node.color) {
                        case 'R':
                        case 'G':
                            if (grid[nr][nc] == 'B') continue;
                            break;
                        case 'B':
                            if (grid[nr][nc] != 'B') continue;
                            break;
                    }
                }
                queue.offer(new Node(nr, nc, grid[nr][nc]));
                visited[nr][nc] = true;
            }
        }
    }

    private static class Node {
        int r, c, color;

        public Node(int r, int c, int color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + color + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_10026_bfs().solution();
    }
}
