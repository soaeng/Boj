package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    두 동전
    https://www.acmicpc.net/problem/16197
*/
public class Boj_16197 {
    static int N, M;
    static char[][] map;
    static Coin[] coins;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        coins = new Coin[2];
        int idx = 0;
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 'o') coins[idx++] = new Coin(r, c);
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        boolean[][][][] visited = new boolean[N][M][N][M];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(coins[0], coins[1]));
        visited[coins[0].r][coins[0].c][coins[1].r][coins[1].c] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (count >= 10) return -1;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int fall = 0;
                    Coin nc1 = new Coin(node.coin1.r + deltas[d][0], node.coin1.c + deltas[d][1]);
                    Coin nc2 = new Coin(node.coin2.r + deltas[d][0], node.coin2.c + deltas[d][1]);
                    if (!isOutOfRange(nc1) && isWall(nc1)) {
                        nc1.r = node.coin1.r;
                        nc1.c = node.coin1.c;
                    }
                    if (!isOutOfRange(nc2) && isWall(nc2)) {
                        nc2.r = node.coin2.r;
                        nc2.c = node.coin2.c;
                    }
                    if (isOutOfRange(nc1)) fall++;
                    if (isOutOfRange(nc2)) fall++;
                    if (fall == 1) return count + 1;
                    else if (fall == 0) {
                        if (visited[nc1.r][nc1.c][nc2.r][nc2.c]) continue;
                        queue.offer(new Node(nc1, nc2));
                        visited[nc1.r][nc1.c][nc2.r][nc2.c] = true;
                    }
                }
            }
            count++;
        }
        return -1;
    }

    private static boolean isWall(Coin coin) {
        return map[coin.r][coin.c] == '#';
    }

    private static boolean isOutOfRange(Coin coin) {
        return coin.r < 0 || coin.r >= N || coin.c < 0 || coin.c >= M;
    }

    private static class Node {
        Coin coin1, coin2;

        public Node(Coin coin1, Coin coin2) {
            this.coin1 = coin1;
            this.coin2 = coin2;
        }

        @Override
        public String toString() {
            return "[" + coin1 + ", " + coin2 + "]";
        }
    }

    private static class Coin {
        int r, c;

        public Coin(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
