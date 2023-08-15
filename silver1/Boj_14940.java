package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    쉬운 최단거리
    https://www.acmicpc.net/problem/14940
*/
public class Boj_14940 {
    static int n, m;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Node> queue;
    static Node start;
    static StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    map[i][j] = 2;
                    start = new Node(i, j);
                } else if (num == 0) map[i][j] = 0;
                else map[i][j] = -1;
            }
        }
        bfs();
        printMap();
        System.out.println(sb);
    }

    private static void bfs() {
        queue = new LinkedList<>();
        queue.offer(start);
        map[start.r][start.c] = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == -1) {
                    map[nr][nc] = map[node.r][node.c] + 1;
                    queue.offer(new Node(nr, nc));
                }
            }
        }
    }

    private static void printMap() {
        for (int[] mm : map) {
            for (int m : mm) {
                sb.append(m).append(" ");
            }
            sb.append("\n");
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
        new Boj_14940().solution();
    }
}
