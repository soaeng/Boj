package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/*
    Puyo Puyo
    https://www.acmicpc.net/problem/11559
*/
public class Boj_11559 {
    static char[][] map;
    static boolean[][] visited;
    static boolean occurrence;
    static int R = 12, C = 6;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int top = 0;
        int count = 0;
        while (true) {
            boolean flag = false;
            occurrence = false;
            visited = new boolean[R][C];
            for (int i = top; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (visited[i][j]) continue;
                    if (map[i][j] != '.') {
                        if (!flag) {
                            top = i;
                            flag = true;
                        }
                        visited[i][j] = true;
                        bfs(i, j);
                    }
                }
            }
            if (!occurrence) break;
            gravity();
            count++;
        }
        System.out.println(count);
    }

    private static void bfs(int r, int c) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        ArrayList<Node> puyos = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();

        puyos.add(new Node(r, c, map[r][c]));
        queue.offer(new Node(r, c, map[r][c]));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] != node.color) continue;
                Node next = new Node(nr, nc, map[nr][nc]);
                puyos.add(next);
                queue.offer(next);
                visited[nr][nc] = true;
            }
        }
        if (puyos.size() >= 4) removePuyo(puyos);
    }

    private static void removePuyo(ArrayList<Node> puyos) {
        occurrence = true;
        for (Node node : puyos) {
            map[node.r][node.c] = '.';
        }
    }

    private static void gravity() {
        for (int c = 0; c < C; c++) {
            for (int r = R - 1; r >= 0; r--) {
                if (map[r][c] == '.') {
                    for (int i = r - 1; i >= 0; i--) {
                        if (map[i][c] != '.') {
                            map[r][c] = map[i][c];
                            map[i][c] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    private static class Node {
        int r, c;
        char color;

        public Node(int r, int c, char color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + color + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_11559().solution();
    }
}
