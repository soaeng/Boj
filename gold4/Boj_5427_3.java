package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    불
    https://www.acmicpc.net/problem/5427
*/
public class Boj_5427_3 {
    static int W, H;
    static char[][] map;
    static Queue<Node> fire;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            fire = new ArrayDeque<>();
            Node start = null;
            for (int r = 0; r < H; r++) {
                String input = br.readLine();
                for (int c = 0; c < W; c++) {
                    map[r][c] = input.charAt(c);
                    if (map[r][c] == '@') {
                        start = new Node(r, c);
                        map[r][c] = '.';
                    } else if (map[r][c] == '*') {
                        fire.offer(new Node(r, c));
                    }
                }
            } // end of input
            int ans = bfs(start);
            sb.append(ans == -1 ? "IMPOSSIBLE" : ans).append("\n");
        }
        System.out.print(sb);
    }

    private static int bfs(Node start) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        int time = 0;
        while (!fire.isEmpty() || !queue.isEmpty()) {
            int size = fire.size();
            for (int i = 0; i < size; i++) {
                Node node = fire.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (map[nr][nc] == '#') continue;
                    if (map[nr][nc] == '*') continue;
                    fire.offer(new Node(nr, nc));
                    map[nr][nc] = '*';
                }
            } // end of fire's queue
            size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) return time + 1;
                    if (map[nr][nc] == '.') {
                        queue.offer(new Node(nr, nc));
                        map[nr][nc] = '@';
                    }
                }
            } // end of human's queue
            time++;
        }
        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
