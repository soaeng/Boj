package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    농장 관리
    https://www.acmicpc.net/problem/1245
*/
public class Boj_1245_2 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static boolean flag = false;
    static int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (visited[r][c]) continue;
                flag = true;
//                dfs(r, c);
                bfs(r, c);
                if (flag) count++;
            }
        }
        System.out.println(count);
    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int d = 0; d < 8; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if (map[nr][nc] > map[r][c]) flag = false;
            if (visited[nr][nc]) continue;
            if (map[nr][nc] == map[r][c]) dfs(nr, nc);
        }
    }

    private static void bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int d = 0; d < 8; d++) {
                int nr = node[0] + deltas[d][0];
                int nc = node[1] + deltas[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (map[nr][nc] > map[r][c]) flag = false;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] != map[r][c]) continue;
                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
