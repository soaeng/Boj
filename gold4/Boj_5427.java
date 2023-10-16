package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    불
    https://www.acmicpc.net/problem/5427
*/
public class Boj_5427 {
    static int w, h;
    static char[][] map;
    static Queue<Node> fire;
    static Node position;
    static StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            fire = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    switch (map[i][j]) {
                        case '@':
                            position = new Node(i, j, 0);
                            break;
                        case '*':
                            fire.add(new Node(i, j, 0));
                            break;
                    }
                }
            }
            int ans = bfs();
            sb.append(ans == -1 ? "IMPOSSIBLE" : ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new LinkedList<>();
        queue.offer(position);
        while (!fire.isEmpty() || !queue.isEmpty()) {
            int size = fire.size();
            for (int i = 0; i < size; i++) {
                Node node = fire.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (checkRange(nr, nc) && map[nr][nc] != '*' && map[nr][nc] != '#') {
                        fire.offer(new Node(nr, nc, node.t + 1));
                        map[nr][nc] = '*';
                    }
                }
            }

            size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (!checkRange(nr, nc)) {
                        return node.t + 1;
                    }
                    if (map[nr][nc] == '.') {
                        queue.offer(new Node(nr, nc, node.t + 1));
                        map[nr][nc] = '@';
                    }
                }
            }
        }
        return -1;
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < h && c >= 0 && c < w;
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
        new Boj_5427().solution();
    }
}
