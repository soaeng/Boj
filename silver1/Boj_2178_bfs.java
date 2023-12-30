package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    미로 탐색
    https://www.acmicpc.net/problem/2178
*/
public class Boj_2178_bfs {
    static int N, M;
    static boolean[][] map;
    static boolean[][] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == '1') map[i][j] = true;
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node[0] + deltas[d][0];
                    int nc = node[1] + deltas[d][1];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (visited[nr][nc] || !map[nr][nc]) continue;
                    if (nr == N - 1 && nc == M - 1) return count + 1;
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        new Boj_2178_bfs().solution();
    }
}
