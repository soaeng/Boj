package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    토마토
    https://www.acmicpc.net/problem/7576
*/
public class Boj_7576_2 {
    static int N, M, count;
    static int[][] box;
    static Queue<int[]> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        queue = new ArrayDeque<>();
        count = 0;
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                switch (box[i][j]) {
                    case -1:
                        count++;
                        break;
                    case 0:
                        flag = true;
                        break;
                    case 1:
                        count++;
                        queue.offer(new int[]{i, j});
                        break;
                }
            }
        }
        if (!flag) {
            System.out.println(0);
            return;
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int day = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            day++;
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node[0] + deltas[d][0];
                    int nc = node[1] + deltas[d][1];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (box[nr][nc] != 0) continue;
                    queue.offer(new int[]{nr, nc});
                    box[nr][nc] = 1;
                    count++;
                }
            }
        }
        if ((N * M) - count > 0) day = -1;
        return day;
    }

    public static void main(String[] args) throws Exception {
        new Boj_7576_2().solution();
    }
}
