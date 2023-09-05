package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    빙산
    https://www.acmicpc.net/problem/2573
*/
public class Boj_2573 {
    static int N, M, time = 0, iceberg;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;
    static ArrayList<Node> heights;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        heights = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) heights.add(new Node(i, j, 0));
            }
        }
        while (iceberg < 2) {
            if (melting()) {
                time = 0;
                break;
            }
            iceberg = 0;
            visited = new boolean[N][M];
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        bfs(i, j);
                        iceberg++;
                    }
                }
            }
            if (iceberg == 0) break;
            time++;
        }
        System.out.println(time);
    }

    private static void bfs(int r, int c) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(r, c, 0));
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (rangeCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
                    queue.offer(new Node(nr, nc, 0));
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static boolean rangeCheck(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static boolean melting() {
        ArrayList<Node> counts = new ArrayList<>();
        for (Node node : heights) {
            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (rangeCheck(nr, nc) && map[nr][nc] == 0) count++;
            }
            if (count > 0) counts.add(new Node(node.r, node.c, count));
        }
        for (Node node : counts) {
            map[node.r][node.c] = Math.max(map[node.r][node.c] - node.count, 0);
        }
        for (Node node : heights) {
            if (map[node.r][node.c] != 0) return false;
        }
        return true;
    }

    private static class Node {
        int r, c, count;

        public Node(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2573().solution();
    }
}
