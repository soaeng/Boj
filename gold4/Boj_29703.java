package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    펭귄의 하루
    https://www.acmicpc.net/problem/29703
*/
public class Boj_29703 {
    static int N, M, hr, hc;
    static char[][] map;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int sr = -1, sc = -1;
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = input.charAt(c);
                switch (map[r][c]) {
                    case 'S':
                        sr = r;
                        sc = c;
                        map[r][c] = 'E';
                        break;
                    case 'H':
                        hr = r;
                        hc = c;
                        map[r][c] = 'E';
                        break;
                }
            }
        }
        System.out.println(bfs(sr, sc));
    }

    private static int bfs(int sr, int sc) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][M];
        queue.offer(new Node(sr, sc));
        visited[0][sr][sc] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.r == hr && node.c == hc && node.fish) return time;
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[node.fish ? 1 : 0][nr][nc]) continue;
                    if (map[nr][nc] == 'D') continue;
                    if (map[nr][nc] == 'F') {
                        queue.offer(new Node(nr, nc, true));
                        visited[1][nr][nc] = true;
                    } else {
                        queue.offer(new Node(nr, nc, node.fish));
                        visited[node.fish ? 1 : 0][nr][nc] = true;
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
        boolean fish;

        public Node(int r, int c) {
            this(r, c, false);
        }

        public Node(int r, int c, boolean fish) {
            this.r = r;
            this.c = c;
            this.fish = fish;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
