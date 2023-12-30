package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    그림
    https://www.acmicpc.net/problem/1926
*/
public class Boj_1926_bfs {
    static int n, m;
    static boolean[][] map;
    static boolean[][] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                if (str.charAt(j * 2) == '1') map[i][j] = true;
            }
        }
        int count = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j]) {
                    count++;
                    max = Math.max(max, bfs(i, j));
                }
            }
        }
        sb.append(count).append("\n").append(max);
        System.out.println(sb);
    }

    private static int bfs(int r, int c) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        int area = 1;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node[0] + deltas[d][0];
                int nc = node[1] + deltas[d][1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (visited[nr][nc] || !map[nr][nc]) continue;
                area++;
                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
        return area;
    }

    public static void main(String[] args) throws Exception {
        new Boj_1926_bfs().solution();
    }
}
