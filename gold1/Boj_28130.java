package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    슈넬치킨 랑데부
    https://www.acmicpc.net/problem/28130
*/
public class Boj_28130 {
    static int N, M, ans;
    static char[][] map;
    static boolean[][] visited;
    static int[][] sanghyuk, sunwoo;
    static Node sang, sun;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        sanghyuk = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(sanghyuk[i], -1);
        }
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                switch (map[i][j]) {
                    case 'A':
                        sang = new Node(i, j, 0);
                        map[i][j] = '.';
                        break;
                    case 'B':
                        sun = new Node(i, j, 0);
                        map[i][j] = '.';
                        break;
                }
            }
        }
        getDistOfSanghyuk();
        getDistOfSunwoo();
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void getDistOfSunwoo() {
        ans = Integer.MAX_VALUE;
        int diameter = 2 * (N + M - 2);
        for (int sw = 0; sw < diameter; sw++) {
            int sh = sanghyuk[sun.r][sun.c];
            if (sh > 0) {
                if (sw > sh) {
                    if ((sw - sh) % 2 == 0) ans = Math.min(ans, sw);
                } else if (sw == sh % diameter) ans = Math.min(ans, sh);
            }
            if (sun.r == 0 && sun.c < M - 1) sun.c++;   // 우
            else if (sun.r < N - 1 && sun.c == M - 1) sun.r++;  // 하
            else if (sun.r == N - 1 && sun.c >= 1 && sun.c < M) sun.c--;    // 좌
            else if (sun.r > 0 && sun.r < N && sun.c == 0) sun.r--; // 상
        }
    }

    private static void getDistOfSanghyuk() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(sang);
        visited[sang.r][sang.c] = true;
        sanghyuk[sang.r][sang.c] = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 'G') continue;
                sanghyuk[nr][nc] = node.t + 1;
                queue.offer(new Node(nr, nc, node.t + 1));
                visited[nr][nc] = true;
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node {
        int r, c, t;

        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_28130().solution();
    }
}
