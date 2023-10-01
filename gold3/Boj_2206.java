package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    벽 부수고 이동하기
    https://www.acmicpc.net/problem/2206
*/
public class Boj_2206 {
    static int N, M, ans = Integer.MAX_VALUE;
    static int[][] map;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) == '0' ? 0 : 1;
            }
        }
        bfs();
        ans = ans == Integer.MAX_VALUE ? -1 : ans;
        System.out.println(ans);
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[2][N][M];
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        visited[1][0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.r == N - 1 && node.c == M - 1) {
                ans = Math.min(node.d + 1, ans);
            }
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (checkRange(nr, nc)) {
                    if (map[nr][nc] == 1) {
                        if (node.b == 0 && !visited[1][nr][nc]) {
                            queue.offer(new Node(nr, nc, 1, node.d + 1));
                            visited[1][nr][nc] = true;
                        }
                    } else {
                        if (!visited[node.b][nr][nc]) {
                            queue.offer(new Node(nr, nc, node.b, node.d + 1));
                            visited[node.b][nr][nc] = true;
                        }
                    }
                }
            }
        }
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Node {
        int r, c, b, d;

        public Node(int r, int c, int b, int d) {
            this.r = r;
            this.c = c;
            this.b = b;
            this.d = d;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + b + ", " + d + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2206().solution();
    }
}
