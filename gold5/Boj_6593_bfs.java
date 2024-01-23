package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    상범 빌딩
    https://www.acmicpc.net/problem/6593
*/
public class Boj_6593_bfs {
    static int L, R, C;
    static boolean[][][] map;
    static Node end;

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new boolean[L][R][C];
            if (L + R + C == 0) break;
            Node start = null;
            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String str = br.readLine();
                    for (int c = 0; c < C; c++) {
                        switch (str.charAt(c)) {
                            case 'S':
                                start = new Node(l, r, c);
                                break;
                            case 'E':
                                end = new Node(l, r, c);
                                break;
                            case '#':
                                map[l][r][c] = true;
                                break;
                        }
                    }
                }
                br.readLine();
            }
            int time = bfs(start);
            if (time == -1) sb.append("Trapped!").append("\n");
            else sb.append("Escaped in ").append(time).append(" minute(s).").append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(Node start) {
        int[][] deltas = {{-1, 0, 0}, {0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}};
        int[][][] visited = new int[L][R][C];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.l][start.r][start.c] = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 6; d++) {
                int nl = node.l + deltas[d][0];
                int nr = node.r + deltas[d][1];
                int nc = node.c + deltas[d][2];
                if (isOutOfRange(nl, nr, nc)) continue;
                if (visited[nl][nr][nc] > 0) continue;
                if (map[nl][nr][nc]) continue;
                if (end.l == nl && end.r == nr && end.c == nc) return visited[node.l][node.r][node.c];
                queue.offer(new Node(nl, nr, nc));
                visited[nl][nr][nc] = visited[node.l][node.r][node.c] + 1;
            }
        }
        return -1;
    }

    private static boolean isOutOfRange(int nl, int nr, int nc) {
        return nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C;
    }

    private static class Node {
        int l, r, c;

        public Node(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_6593_bfs().solution();
    }
}
