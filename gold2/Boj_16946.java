package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    벽 부수고 이동하기 4
    https://www.acmicpc.net/problem/16946
*/
public class Boj_16946 {
    static int N, M;
    static boolean[][] map;
    static boolean[][] visited;
    static int[][][] count;
    static ArrayList<Node> walls;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        visited = new boolean[N][M];
        count = new int[N][M][2];
        walls = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == '1') {
                    map[i][j] = true;
                    walls.add(new Node(i, j));
                }
            }
        }
        int seq = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (!map[i][j]) bfs(i, j, seq++);
            }
        }
        getCount();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) sb.append(count[i][j][1] % 10);
                else sb.append(0);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void getCount() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (Node node : walls) {
            int sum = 0;
            int[] seq = new int[4];
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (map[nr][nc]) continue;
                if (!isAnotherSpace(seq, nr, nc)) continue;
                seq[d] = count[nr][nc][0];
                sum += count[nr][nc][1];
            }
            count[node.r][node.c][1] = sum + 1;
        }
    }

    private static boolean isAnotherSpace(int[] seq, int r, int c) {
        for (int s : seq) {
            if (s == count[r][c][0]) return false;
        }
        return true;
    }

    private static void bfs(int r, int c, int seq) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(r, c));
        visited[r][c] = true;
        ArrayList<Node> list = new ArrayList<>();
        list.add(new Node(r, c));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc]) continue;
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = true;
                list.add(new Node(nr, nc));
            }
        }
        int size = list.size();
        for (Node node : list) {
            count[node.r][node.c][0] = seq;
            count[node.r][node.c][1] = size;
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
        new Boj_16946().solution();
    }
}
