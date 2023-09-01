package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
    적록색약
    https://www.acmicpc.net/problem/10026
*/
public class Boj_10026 {
    static int N, a = 0, b = 0;
    static char[][] map;
    static boolean[][] visitedA;
    static boolean[][] visitedB;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visitedA = new boolean[N][N];
        visitedB = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visitedA[i][j]) {
                    bfsA(i, j);
                    a++;
                }
                if (!visitedB[i][j]) {
                    bfsB(i, j);
                    b++;
                }
            }
        }
        sb.append(a).append(" ").append(b);
        System.out.println(sb);
    }

    private static void bfsA(int r, int c) {
        queue = new LinkedList<>();
        queue.add(new Node(r, c));
        visitedA[r][c] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visitedA[nr][nc]) {
                    if (map[nr][nc] == map[r][c]) {
                        queue.offer(new Node(nr, nc));
                        visitedA[nr][nc] = true;
                    }
                }
            }
        }
    }

    private static void bfsB(int r, int c) {
        queue = new LinkedList<>();
        queue.add(new Node(r, c));
        visitedB[r][c] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visitedB[nr][nc]) {
                    if (map[r][c] == 'R' || map[r][c] == 'G') {
                        if (map[nr][nc] == 'R' || map[nr][nc] == 'G') {
                            queue.offer(new Node(nr, nc));
                            visitedB[nr][nc] = true;
                        }
                    } else {
                        if (map[nr][nc] == 'B') {
                            queue.offer(new Node(nr, nc));
                            visitedB[nr][nc] = true;
                        }
                    }
                }
            }
        }
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_10026().solution();
    }
}
