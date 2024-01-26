package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    유기농 배추
    https://www.acmicpc.net/problem/1012
*/
public class Boj_1012 {
    static int T, M, N, K, ans = 0;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            ans = 0;
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                map[n][m] = 1;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        bfs(i, j);
                        ans++;
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int i, int j) {
        queue = new LinkedList<>();
        queue.offer(new Node(i, j));
        Node node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1) {
                    queue.offer(new Node(nr, nc));
                    map[nr][nc] = 0;
                }
            }
        }
    }

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_1012().solution();
    }
}
