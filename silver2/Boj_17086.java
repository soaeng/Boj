package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    아기 상어 2
    https://www.acmicpc.net/problem/17086
*/
public class Boj_17086 {
    static int N, M, max = 0;
    static int[][] map, distance, deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    static Queue<Node> queue = new LinkedList<>();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        distance = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) queue.add(new Node(i, j));
                else distance[i][j] = -1;
            }
        }
        bfs();
        System.out.println(max);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 8; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && distance[nr][nc] == -1) {
                    queue.add(new Node(nr, nc));
                    distance[nr][nc] = distance[node.r][node.c] + 1;
                    if (max < distance[nr][nc]) max = distance[nr][nc];
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
        new Boj_17086().solution();
    }
}
