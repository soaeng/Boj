package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    미로 탈출
    https://www.acmicpc.net/problem/14923
*/
public class Boj_14923 {
    static int N, M, D = 0;
    static int[][] maze;
    static Queue<Node> wall;
    static Node start, end;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.MAX_VALUE;
        maze = new int[N][M];
        wall = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        start = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        start.d = 0;
        st = new StringTokenizer(br.readLine());
        end = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
                if (maze[i][j] == 1) wall.add(new Node(i, j));
            }
        }
        bfs();
        System.out.println(D == Integer.MAX_VALUE ? -1 : D);
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][][] visited = new boolean[2][N][M];
        queue.offer(start);
        visited[0][start.r][start.c] = true;
        visited[1][start.r][start.c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.r == end.r && node.c == end.c) {
                D = Math.min(node.d, D);
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if (maze[nr][nc] == 1) {
                        if (node.b == 0 && !visited[1][nr][nc]) {
                            queue.offer(new Node(nr, nc, node.d + 1, 1));
                            visited[1][nr][nc] = true;
                        }
                    } else {
                        if (!visited[node.b][nr][nc]) {
                            queue.offer(new Node(nr, nc, node.d + 1, node.b));
                            visited[node.b][nr][nc] = true;
                        }
                    }
                }
            }
        }
    }

    private static class Node {
        int r, c, d, b;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
            this.d = 0;
            this.b = 0;
        }

        public Node(int r, int c, int d, int b) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.b = b;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_14923().solution();
    }
}
