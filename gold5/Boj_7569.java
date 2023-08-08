package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    토마토
    https://www.acmicpc.net/problem/7569
*/
public class Boj_7569 {
    static int N, M, H, MAX = -1;
    static int[][][] box;
    static int[] dn = {-1, 1, 0, 0, 0, 0};
    static int[] dm = {0, 0, -1, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};
    static boolean flag;
    static Queue<Tomato> queue = new LinkedList<>();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[H][N][M];
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(st.nextToken());
                    if (box[h][n][m] == 1) queue.add(new Tomato(h, n, m));
                    if (box[h][n][m] == 0) flag = true;
                }
            }
        }
        if (!flag) {
            System.out.println(0);
            return;
        }
        bfs();
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 0) {
                        MAX = 0;
                        break;
                    }
                }
            }
        }
        System.out.println(MAX - 1);
    }

    static void bfs() {
        Tomato tomato;
        while (!queue.isEmpty()) {
            tomato = queue.poll();
            for (int d = 0; d < 6; d++) {
                int nh = tomato.h + dh[d];
                int nn = tomato.n + dn[d];
                int nm = tomato.m + dm[d];
                if (nh >= 0 && nh < H && nn >= 0 && nn < N && nm >= 0 && nm < M && box[nh][nn][nm] == 0) {
                    box[nh][nn][nm] = box[tomato.h][tomato.n][tomato.m] + 1;
                    if (MAX < box[nh][nn][nm]) MAX = box[nh][nn][nm];
                    queue.offer(new Tomato(nh, nn, nm));
                }
            }
        }
    }

    static void printBox() {
        for (int[][] bbb : box) {
            System.out.println("===========");
            for (int[] bb : bbb) {
                for (int b : bb) {
                    System.out.print(b + " ");
                }
                System.out.println();
            }
        }
    }

    private static class Tomato {
        int h, n, m;

        public Tomato(int h, int n, int m) {
            this.h = h;
            this.n = n;
            this.m = m;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_7569().solution();
    }
}
