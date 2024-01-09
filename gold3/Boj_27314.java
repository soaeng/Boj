package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    러키☆한별
    https://www.acmicpc.net/problem/27314
*/
public class Boj_27314 {
    static int N, M;
    static Node hanbyeol;
    static char[][] map;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        ArrayList<Node> exits = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                switch (map[i][j]) {
                    case '#':
                        exits.add(new Node(i, j));
                        break;
                    case 'H':
                        hanbyeol = new Node(i, j);
                        break;
                }
            }
        }
        for (Node exit : exits) {
            max = Math.max(max, bfs(exit.r, exit.c));
        }
        System.out.println(max);
    }

    private static int bfs(int r, int c) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        int[][] visited = new int[N][M];
        queue.offer(new Node(r, c));
        visited[r][c] = 1;

        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (visited[nr][nc] > 0 || map[nr][nc] == 'X') continue;
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = visited[node.r][node.c] + 1;
            }
        }

        if (visited[hanbyeol.r][hanbyeol.c] == 0) return 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 'P' || visited[i][j] == 0) continue;
                if (visited[hanbyeol.r][hanbyeol.c] >= visited[i][j]) count++;
            }
        }
        return count;
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
        new Boj_27314().solution();
    }
}
