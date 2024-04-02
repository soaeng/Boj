package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    경쟁적 전염
    https://www.acmicpc.net/problem/18405
*/
public class Boj_18405_2 {
    static int N, K, S;
    static int[][] map;
    static Queue<Node>[] virus;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virus = new Queue[K + 1];
        for (int k = 1; k <= K; k++) {
            virus[k] = new ArrayDeque<>();
        }
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] > 0) virus[map[r][c]].offer(new Node(r, c));
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        if (map[r][c] != 0) {
            System.out.println(map[r][c]);
            return;
        }
        bfs();
        System.out.println(map[r][c]);
    }

    private static void bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[N][N];
        boolean flag = false;
        while (!flag && S-- > 0) {
            flag = true;
            for (int i = 1; i <= K; i++) {
                int size = virus[i].size();
                if (size > 0) flag = false;
                for (int j = 0; j < size; j++) {
                    Node node = virus[i].poll();
                    for (int d = 0; d < 4; d++) {
                        int nr = node.r + deltas[d][0];
                        int nc = node.c + deltas[d][1];
                        if (isOutOfRange(nr, nc)) continue;
                        if (visited[nr][nc]) continue;
                        if (map[nr][nc] != 0) continue;
                        virus[i].offer(new Node(nr, nc));
                        visited[nr][nc] = true;
                        map[nr][nc] = i;
                    }
                }
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static class Node {
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
