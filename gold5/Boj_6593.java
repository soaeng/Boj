package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    상범 빌딩
    https://www.acmicpc.net/problem/
*/
public class Boj_6593 {
    public void solution() throws Exception {
        int L, R, C;
        char[][][] map;
        Node node = new Node();
        int time;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[L][R][C];
            if (L == 0 && R == 0 && C == 0) break;
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = str.charAt(k);
                        if (map[i][j][k] == 'S') {
                            node.h = i;
                            node.r = j;
                            node.c = k;
                        }
                    }
                }
                br.readLine();
            }
            time = bfs(L, R, C, map, node);
            if (time == -1) sb.append("Trapped!").append("\n");
            else sb.append("Escaped in ").append(time).append(" minute(s).").append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(int L, int R, int C, char[][][] map, Node start) {
        int[][] deltas = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {-1, 0, 0}, {1, 0, 0}};
        int[][][] visited = new int[L][R][C];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.h][start.r][start.c] = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 6; d++) {
                int nh = node.h + deltas[d][0];
                int nr = node.r + deltas[d][1];
                int nc = node.c + deltas[d][2];
                if (nh >= 0 && nh < L && nr >= 0 && nr < R && nc >= 0 && nc < C && visited[nh][nr][nc] == 0 && map[nh][nr][nc] != '#') {
                    if (map[nh][nr][nc] == 'E') return visited[node.h][node.r][node.c];
                    visited[nh][nr][nc] = visited[node.h][node.r][node.c] + 1;
                    queue.offer(new Node(nh, nr, nc));
                }
            }
        }
        return -1;
    }

    private static class Node {
        int h, r, c;

        public Node() {
        }

        public Node(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_6593().solution();
    }
}
