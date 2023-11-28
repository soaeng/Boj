package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    탈출
    https://www.acmicpc.net/problem/3055
*/
public class Boj_3055 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Node> queue;
    static Queue<Node> hedgehog;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        queue = new LinkedList<>();
        hedgehog = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 'S') hedgehog.offer(new Node(r, c, 0));
                if (map[r][c] == '*') {
                    queue.offer(new Node(r, c, 0));
                    visited[r][c] = true;
                }
            }
        }
        int ans = bfs();
        System.out.println(ans == -1 ? "KAKTUS" : ans);
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty() || !hedgehog.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isInRange(nr, nc)) continue;
                    if (visited[nr][nc] || map[nr][nc] == 'X' || map[nr][nc] == 'D') continue;
                    queue.offer(new Node(nr, nc, node.t + 1));
                    visited[nr][nc] = true;
                    map[nr][nc] = '*';
                }
            }
            size = hedgehog.size();
            for (int i = 0; i < size; i++) {
                Node node = hedgehog.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isInRange(nr, nc)) continue;
                    if (map[nr][nc] == 'D') return node.t + 1;
                    if (map[nr][nc] != '.') continue;
                    hedgehog.offer(new Node(nr, nc, node.t + 1));
                    map[nr][nc] = 'S';
                }
            }
        }
        return -1;
    }

    private static boolean isInRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    private static class Node {
        int r, c, t;

        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_3055().solution();
    }
}
