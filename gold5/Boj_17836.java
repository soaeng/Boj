package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    공주님을 구해라!
    https://www.acmicpc.net/problem/17836
*/
public class Boj_17836 {
    static int N, M;
    static char[][] map;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = input.charAt(c * 2);
            }
        }
        int time = bfs();
        System.out.println(time == -1 || time > T ? "Fail" : time);
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][M];
        queue.offer(new Node(0, 0, false));
        visited[0][0][0] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.r == N - 1 && node.c == M - 1) return time;
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[node.sword ? 1 : 0][nr][nc]) continue;
                    if (map[nr][nc] == '1') {
                        if (!node.sword) continue;
                        queue.offer(new Node(nr, nc, true));
                        visited[1][nr][nc] = true;
                    } else if (map[nr][nc] == '2') {
                        queue.offer(new Node(nr, nc, true));
                        visited[0][nr][nc] = true;
                        map[nr][nc] = '0';
                    } else {
                        queue.offer(new Node(nr, nc, node.sword));
                        visited[node.sword ? 1 : 0][nr][nc] = true;
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
        boolean sword;

        public Node(int r, int c, boolean sword) {
            this.r = r;
            this.c = c;
            this.sword = sword;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
