package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    아맞다우산
    https://www.acmicpc.net/problem/17244
*/
public class Boj_17244 {
    static int R, C;
    static char[][] map;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        Node start = null;
        char item = 'a';
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 'S') start = new Node(r, c, 0);
                else if (map[r][c] == 'X') map[r][c] = item++;
            }
        }
        System.out.println(bfs(start, item - 'a'));
    }

    private static int bfs(Node start, int X) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[1 << X][R][C];
        queue.offer(start);
        visited[0][start.r][start.c] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (map[node.r][node.c] == 'E' && (node.item == (1 << X) - 1)) return time;
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[node.item][nr][nc]) continue;
                    if (map[nr][nc] == '#') continue;
                    int ni = node.item;
                    if (map[nr][nc] >= 'a' && map[nr][nc] <= 'e')
                        ni |= (1 << map[nr][nc] - 'a');
                    queue.offer(new Node(nr, nc, ni));
                    visited[ni][nr][nc] = true;
                }
            }
            time++;
        }
        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    private static class Node {
        int r, c, item;

        public Node(int r, int c, int item) {
            this.r = r;
            this.c = c;
            this.item = item;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + item + "(" + Integer.toBinaryString(item) + ") )";
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
