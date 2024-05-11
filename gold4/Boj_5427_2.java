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
public class Boj_5427_2 {
    static int W, H;
    static char[][] map;
    static int[][] visitedFire, visitedHuman;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Node> fire = new ArrayDeque<>();

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            visitedFire = new int[H][W];
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
                        visitedFire[r][c] = 1;
                    }
                }
            } // end of input
            bfsFire();
            int result = bfsHuman(start);
            sb.append(result < 0 ? "IMPOSSIBLE" : result).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfsFire() {
        while (!fire.isEmpty()) {
            Node node = fire.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (visitedFire[nr][nc] != 0) continue;
                if (map[nr][nc] == '#') continue;
                fire.offer(new Node(nr, nc));
                visitedFire[nr][nc] = visitedFire[node.r][node.c] + 1;
            }
        }
    }

    private static int bfsHuman(Node start) {
        Queue<Node> queue = new ArrayDeque<>();
        visitedHuman = new int[H][W];
        queue.offer(start);
        visitedHuman[start.r][start.c] = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) return visitedHuman[node.r][node.c];
                if (visitedHuman[nr][nc] != 0) continue;
                if (map[nr][nc] == '#') continue;
                if (visitedFire[nr][nc] > 0 && visitedFire[nr][nc] <= visitedHuman[node.r][node.c] + 1) continue;
                queue.offer(new Node(nr, nc));
                visitedHuman[nr][nc] = visitedHuman[node.r][node.c] + 1;
            }
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
