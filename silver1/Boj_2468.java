package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    안전 영역
    https://www.acmicpc.net/problem/2468
    채점 정보
    bfs(55412KB, 312ms)
    dfs(19588KB, 272ms)
*/
public class Boj_2468 {
    static int N, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        int min = 101, max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (max < map[i][j]) max = map[i][j];
                if (min > map[i][j]) min = map[i][j];
            }
        }
        getSafetyArea(min, max);
        System.out.println(ans);
    }

    static void getSafetyArea(int min, int max) {
        for (int h = min - 1; h <= max; h++) {
            visited = new boolean[N][N];
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= h) continue;
                    if (visited[i][j]) continue;
                    count++;
//                    bfs(i, j, h);
                    dfs(i, j, h);
                }
            }
            if (ans < count) ans = count;
        }
    }

    static void dfs(int r, int c, int h) {
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (visited[nr][nc]) continue;
            if (map[nr][nc] <= h) continue;
            dfs(nr, nc, h);
        }
    }

    static void bfs(int r, int c, int h) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(r, c));
        visited[r][c] = true;
        Node node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] <= h) continue;
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = true;
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
        solution();
    }
}
