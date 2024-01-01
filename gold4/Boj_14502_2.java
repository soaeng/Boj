package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    연구소
    https://www.acmicpc.net/problem/14502
*/
public class Boj_14502_2 {
    static int N, M, ans;
    static int[][] map;
    static ArrayList<int[]> virus;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        virus = new ArrayList<>();
        ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new int[]{i, j});
            }
        }
        dfs(0, 0, 0);
        System.out.println(ans);
    }

    private static void dfs(int r, int c, int cnt) {
        if (cnt == 3) {
            ans = Math.max(ans, bfs());
            return;
        }

        for (int i = r; i < N; i++) {
            for (int j = c; j < M; j++) {
                if (map[i][j] != 0) continue;
                map[i][j] = 1;
                dfs(i, j, cnt + 1);
                map[i][j] = 0;
            }
        }
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] temp = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }
        Queue<int[]> queue = new ArrayDeque<>(virus);
        for (int[] node : virus) {
            visited[node[0]][node[1]] = true;
        }
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node[0] + deltas[d][0];
                int nc = node[1] + deltas[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc]) continue;
                if (temp[nr][nc] != 0) continue;
                queue.offer(new int[]{nr, nc});
                temp[nr][nc] = 2;
                visited[nr][nc] = true;
            }
        }
        return getArea(temp);
    }

    private static int getArea(int[][] map) {
        int area = 0;
        for (int[] mm : map) {
            for (int m : mm) {
                if (m == 0) area++;
            }
        }
        return area;
    }

    private static int[][] setMap() {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }
        for (int[] node : virus) {
            temp[node[0]][node[1]] = 2;
        }
        return temp;
    }

    public static void main(String[] args) throws Exception {
        new Boj_14502_2().solution();
    }
}

