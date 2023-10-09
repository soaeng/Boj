package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    수영장 만들기
    https://www.acmicpc.net/problem/1113
*/
public class Boj_1113 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int max = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(str.charAt(j));
                max = Math.max(map[i][j] + 1, max);
            }
        }
        int ans = 0;
        for (int i = 2; i < max; i++) {
            visited = new boolean[N][M];
            for (int r = 1; r < N - 1; r++) {
                for (int c = 1; c < M - 1; c++) {
                    if (map[r][c] < i && !visited[r][c]) ans += bfs(r, c, i);
                }
            }
        }
        System.out.println(ans);
    }

    private static int bfs(int r, int c, int h) {
        Queue<Node> queue = new LinkedList<>();
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int area = 1;
        boolean edge = false;
        queue.offer(new Node(r, c));
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (!checkRange(nr, nc)) {
                    edge = true;
                    continue;
                }
                if (!visited[nr][nc] && map[nr][nc] < h) {
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                    area++;
                }
            }
        }
        return edge ? 0 : area;
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
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
        new Boj_1113().solution();
    }
}
