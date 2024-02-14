package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    영역 구하기
    https://www.acmicpc.net/problem/2583
*/
public class Boj_2583 {
    static int M, N, K;
    static boolean[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int r = y1; r < y2; r++) {
                for (int c = x1; c < x2; c++)
                    map[r][c] = true;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                if (map[i][j]) continue;
                list.add(bfs(i, j));
            }
        }
        Collections.sort(list);
        sb.append(list.size()).append("\n");
        for (int n : list) sb.append(n).append(" ");
        System.out.println(sb);
    }

    private static int bfs(int r, int c) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(r, c));
        visited[r][c] = true;
        int area = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (map[nr][nc]) continue;
                if (visited[nr][nc]) continue;
                area++;
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = true;
            }
        }
        return area;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= M || c < 0 || c >= N;
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
