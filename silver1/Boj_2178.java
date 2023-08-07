package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    미로 탐색
    https://www.acmicpc.net/problem/2178
*/
public class Boj_2178 {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs(0, 0, 1));
    }

    static int bfs(int n, int m, int count) {
        queue = new LinkedList<>();
        queue.offer(new Node(n, m, count));
        visited[n][m] = true;
        Node node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            count = node.count;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr == N - 1 && nc == M - 1) return count + 1;
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == '1' && !visited[nr][nc]) {
                    queue.offer(new Node(nr, nc, count + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        return 0;
    }

    static class Node {
        int r, c, count;

        public Node(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    static void printMap() {
        for (char[] mm : map) {
            for (char m : mm) {
                System.out.print(m);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2178().solution();
    }
}
