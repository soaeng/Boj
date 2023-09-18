package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    말이 되고픈 원숭이
    https://www.acmicpc.net/problem/1600
*/
public class Boj_1600 {
    static int K, W, H;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[K + 1][H][W];
        queue.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.r == H - 1 && node.c == W - 1) {
                return node.d;
            }
            for (int d = 0; d < 12; d++) {
                if (node.cnt >= K && d >= 4) break;
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (checkRange(nr, nc) && map[nr][nc] == 0) {
                    if (d >= 4 && !visited[node.cnt + 1][nr][nc]) {
                        queue.offer(new Node(nr, nc, node.d + 1, node.cnt + 1));
                        visited[node.cnt + 1][nr][nc] = true;
                    } else if (d < 4 && !visited[node.cnt][nr][nc]) {
                        queue.offer(new Node(nr, nc, node.d + 1, node.cnt));
                        visited[node.cnt][nr][nc] = true;
                    }
                }
            }
        }
        return -1;
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }

    private static class Node {
        int r, c, d, cnt;

        public Node(int r, int c, int d, int cnt) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_1600().solution();
    }
}
