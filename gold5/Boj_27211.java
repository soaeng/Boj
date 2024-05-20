package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    도넛 행성
    https://www.acmicpc.net/problem/27211
*/
public class Boj_27211 {
    static int N, M;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                if (st.nextToken().equals("1")) visited[r][c] = true;
            }
        }
        int count = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (visited[r][c]) continue;
                bfs(r, c);
                count++;
            }
        }
        System.out.println(count);
    }

    private static void bfs(int sr, int sc) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(sr, sc));
        visited[sr][sc] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = (node.r + deltas[d][0] + N) % N;
                int nc = (node.c + deltas[d][1] + M) % M;
                if (visited[nr][nc]) continue;
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = true;
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
        solution();
    }
}
