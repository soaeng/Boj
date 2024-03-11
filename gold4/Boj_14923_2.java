package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    미로 탈출
    https://www.acmicpc.net/problem/14923
*/
public class Boj_14923_2 {
    static int N, M, er, ec;
    static boolean[][] map;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                if (input.charAt(c * 2) == '1') map[r][c] = true;
            }
        }
        System.out.println(bfs(sr, sc));
    }

    private static int bfs(int sr, int sc) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][M];
        int time = 0;

        queue.offer(new Node(sr, sc, false));
        visited[0][sr][sc] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.r == er && node.c == ec) return time;
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[node.isBroken ? 1 : 0][nr][nc]) continue;
                    if (map[nr][nc]) {
                        if (node.isBroken) continue;
                        queue.offer(new Node(nr, nc, true));
                        visited[1][nr][nc] = true;
                    } else {
                        queue.offer(new Node(nr, nc, node.isBroken));
                        visited[node.isBroken ? 1 : 0][nr][nc] = true;
                    }
                }
            }
            time++;
        }
        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node {
        int r, c;
        boolean isBroken;

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
