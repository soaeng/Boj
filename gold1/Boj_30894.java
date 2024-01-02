package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    유령의 집 탈출하기
    https://www.acmicpc.net/problem/30894
*/
public class Boj_30894 {
    static int N, M, er, ec;
    static char[][] map;
    static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {0, 0}}; // 우하좌상
    static ArrayList<Node> ghost;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        ghost = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int[] start = new int[]{r, c};
        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] >= '0' && map[i][j] <= '3') {
                    ghost.add(new Node(i, j, map[i][j] - '0', 0));
                    map[i][j] = '#';
                }
            }
        }
        int ans = bfs(start);
        System.out.println(ans == -1 ? "GG " : ans);
    }

    static int bfs(int[] start) {
        int state = -1;
        boolean[][][] visited = setVisible();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start[0], start[1]));
        while (!queue.isEmpty()) {
            int size = queue.size();
            state++;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.r == er && node.c == ec) return node.t;
                for (int d = 0; d < 5; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (map[nr][nc] == '#') continue;
                    if (visited[state % 4][nr][nc]) continue;
                    queue.offer(new Node(nr, nc, d, node.t + 1));
                    visited[state % 4][nr][nc] = true;
                }
            }
        }
        return -1;
    }

    static boolean[][][] setVisible() {
        boolean[][][] vision = new boolean[4][N][M];
        for (int state = 0; state < 4; state++) {
            for (Node node : ghost) {
                node.d = (node.d + 1) % 4;
                int nr = node.r;
                int nc = node.c;
                do {
                    nr += deltas[node.d][0];
                    nc += deltas[node.d][1];
                    if (isOutOfRange(nr, nc)) break;
                    if (map[nr][nc] != '.') break;
                    vision[state][nr][nc] = true;
                } while (map[nr][nc] == '.');
            }
        }
        return vision;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    static class Node {
        int r, c, d, t;

        public Node(int r, int c) {
            this(r, c, -1, 0);
        }

        public Node(int r, int c, int d, int t) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.t = t;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + d + ", " + t + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_30894().solution();
    }
}
