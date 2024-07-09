package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    마알
    https://www.acmicpc.net/problem/1795
*/
public class Boj_1795 {
    static int N, M;
    static int[][] moves, dest;
    static int[][] deltas = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    static ArrayList<Knight> list;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                int k = input.charAt(c) - '0';
                if (k > 0) list.add(new Knight(r, c, k, 1));
            }
        }

        if (list.size() == 1) {
            System.out.println(0);
            return;
        } else if (N == 1 || M == 1) {
            System.out.println(-1);
            return;
        }

        int min = Integer.MAX_VALUE;
        moves = new int[N][M];
        dest = new int[N][M];

        for (Knight knight : list) bfs(knight);

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (dest[r][c] == list.size()) min = Math.min(min, moves[r][c]);
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void bfs(Knight knight) {
        Queue<Knight> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(knight);
        visited[knight.r][knight.c] = true;
        dest[knight.r][knight.c]++;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Knight node = queue.poll();
                if (node.k == 0) {
                    node.k = knight.k;
                    node.m++;
                }
                for (int d = 0; d < 8; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[nr][nc]) continue;
                    queue.offer(new Knight(nr, nc, node.k - 1, node.m));
                    visited[nr][nc] = true;
                    dest[nr][nc]++;
                    moves[nr][nc] += node.m;
                }
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Knight {
        int r, c, k, m;

        public Knight(int r, int c, int k, int m) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.m = m;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
