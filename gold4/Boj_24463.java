package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    미로
    https://www.acmicpc.net/problem/24463
*/
public class Boj_24463 {
    static int N, M;
    static Node[] hole;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static StringBuilder sb = new StringBuilder();

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        hole = new Node[2];
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = input.charAt(c);
                if (map[r][c] == '.') {
                    if (r == 0 || r == N - 1 || c == 0 || c == M - 1) {
                        if (hole[0] == null) hole[0] = new Node(r, c);
                        else hole[1] = new Node(r, c);
                    }
                    map[r][c] = '@';
                }
            }
        }
        dfs(hole[0].r, hole[0].c, map);
        System.out.println(sb);
    }

    private static void dfs(int r, int c, char[][] temp) {
        if (r == hole[1].r && c == hole[1].c) {
            temp[hole[0].r][hole[0].c] = '.';
            for (char[] mm : temp) {
                for (char m : mm) sb.append(m);
                sb.append("\n");
            }
            return;
        }
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (isOutOfRange(nr, nc)) continue;
            if (temp[nr][nc] == '@') {
                temp[nr][nc] = '.';
                dfs(nr, nc, temp);
                temp[nr][nc] = '@';
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
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
