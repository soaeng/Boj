package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    보물섬
    https://www.acmicpc.net/problem/2589
*/
public class Boj_2589 {
    static int N, M, MAX = 0;
    static char[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited = new boolean[N][M];
                if (map[i][j] == 'L' && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(MAX);
    }

    private static void bfs(int r, int c) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(r, c, 0));
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] != 'L') continue;
                queue.offer(new Node(nr, nc, node.t + 1));
                visited[nr][nc] = true;
                if (MAX < node.t + 1) MAX = node.t + 1;
            }
        }
    }

    private static class Node {
        int r, c, t;

        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2589().solution();
    }
}
