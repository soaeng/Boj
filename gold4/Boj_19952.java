package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    인성 문제 있어??
    https://www.acmicpc.net/problem/19952
*/
public class Boj_19952 {
    static int H, W, XE, YE;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] map;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            int O = Integer.parseInt(st.nextToken());
            int F = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken()) - 1;
            int Y = Integer.parseInt(st.nextToken()) - 1;
            Node start = new Node(X, Y, F);
            XE = Integer.parseInt(st.nextToken()) - 1;
            YE = Integer.parseInt(st.nextToken()) - 1;
            while (O-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                map[x][y] = Integer.parseInt(st.nextToken());
            }
            System.out.println(bfs(start) ? "잘했어!!" : "인성 문제있어??");
        }
    }

    private static boolean bfs(Node start) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];
        queue.offer(start);
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.f == 0) continue;
            for (int d = 0; d < 4; d++) {
                int nx = node.x + deltas[d][0];
                int ny = node.y + deltas[d][1];
                if (isOutOfRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (node.f < map[nx][ny] - map[node.x][node.y]) continue;
                if (nx == XE && ny == YE) return true;
                queue.offer(new Node(nx, ny, node.f - 1));
                visited[nx][ny] = true;
            }
        }
        return false;
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= H || y < 0 || y >= W;
    }

    private static class Node {
        int x, y, f;

        public Node(int x, int y, int f) {
            this.x = x;
            this.y = y;
            this.f = f;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
