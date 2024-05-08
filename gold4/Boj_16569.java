package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    화산쇄설류
    https://www.acmicpc.net/problem/16569
*/
public class Boj_16569 {
    static int M, N, height, min;
    static int[][] map;
    static ArrayList<Volcano> list;

    private static void solution() throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;

        for (int r = 0; r < M; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        while (V-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            map[r][c] = -1;
            list.add(new Volcano(r, c, t));
        }
        bfs(sr, sc);
        sb.append(height).append(" ").append(min);
        System.out.println(sb);
    }

    private static void bfs(int r, int c) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        int time = 1;

        queue.offer(new Node(r, c));
        height = map[r][c];
        map[r][c] = -1;
        min = 0;

        while (!queue.isEmpty()) {
            for (Volcano volcano : list) {
                if (volcano.t > time) continue;
                for (int x = 0; x < M; x++) {
                    for (int y = 0; y < N; y++) {
                        if (map[x][y] == -1) continue;
                        if (time - volcano.t >= Math.abs(x - volcano.r) + Math.abs(y - volcano.c)) {
                            map[x][y] = -1;
                        }
                    }
                }
            }

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (map[nr][nc] == -1) continue;
                    queue.offer(new Node(nr, nc));
                    if (height < map[nr][nc]) {
                        height = map[nr][nc];
                        min = time;
                    }
                    map[nr][nc] = -1;
                }
            }
            time++;
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= M || c < 0 || c >= N;
    }

    private static class Volcano implements Comparable<Volcano> {
        int r, c, t;

        public Volcano(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

        @Override
        public int compareTo(Volcano volcano) {
            return this.t - volcano.t;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + t + ")";
        }
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
