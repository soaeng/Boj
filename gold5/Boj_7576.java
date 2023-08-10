package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    토마토
    https://www.acmicpc.net/problem/7576
*/
public class Boj_7576 {
    static int M, N, ans;
    static int[][] box;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Tomato> queue = new LinkedList<>();
    static boolean flag = false;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) queue.offer(new Tomato(i, j));
                if (box[i][j] == 0) flag = true;
            }
        }
        if (flag) bfs();
        else {
            System.out.println(0);
            return;
        }
        for (int[] nn : box) {
            for (int n : nn) {
                if (n == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(ans - 1);
    }

    static void bfs() {
        Tomato tomato;
        while (!queue.isEmpty()) {
            tomato = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = tomato.r + deltas[d][0];
                int nc = tomato.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && box[nr][nc] == 0) {
                    box[nr][nc] = box[tomato.r][tomato.c] + 1;
                    queue.offer(new Tomato(nr, nc));
                    if (ans < box[nr][nc]) ans = box[nr][nc];
                }
            }
        }
    }

    static class Tomato {
        int r, c;

        public Tomato(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_7576().solution();
    }
}
