package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    좀비 바이러스
    https://www.acmicpc.net/problem/24513
*/
public class Boj_24513 {
    static int N, M;
    static int[][] map;
    static int[][] time;
    static int[] count;
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        time = new int[N][M];
        count = new int[4];
        queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    queue.offer(new Node(i, j));
                    time[i][j] = 1;
                }
            }
        }
        bfs();

        for (int[] nn : map) {
            for (int n : nn) {
                if (n == -1) continue;
                count[n]++;
            }
        }
        for (int i = 1; i < 4; i++) {
            sb.append(count[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int current = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            current++;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (map[node.r][node.c] == 3) continue;
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (map[nr][nc] < 0) continue;
                    if (map[nr][nc] == 3) continue;
                    if (map[nr][nc] == 0) {
                        map[nr][nc] = map[node.r][node.c];
                        queue.offer(new Node(nr, nc));
                        time[nr][nc] = current;
                    } else {
                        if (time[nr][nc] != current) continue;
                        if (map[nr][nc] != map[node.r][node.c]) {
                            time[nr][nc] = -1;
                            map[nr][nc] = 3;
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
        new Boj_24513().solution();
    }
}
