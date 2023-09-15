package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    다리 만들기
    https://www.acmicpc.net/problem/2146
*/
public class Boj_2146 {
    static int N, island, ans;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static boolean[][] visited;
    static ArrayList<Node> land;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        land = new ArrayList<>();
        ans = Integer.MAX_VALUE;
        island = 1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) land.add(new Node(i, j));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j, island);
                    island++;
                }
            }
        }

        for (Node node : land) {
            bfs(node);
        }
        System.out.println(ans);
    }

    private static void dfs(int r, int c, int n) {
        map[r][c] = n;
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == 1) {
                dfs(nr, nc, n);
            }
        }
    }
//
//    private static void setNum() {
//        visited = new boolean[N][N];
//        island = 1;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                if (map[i][j] == 1 && !visited[i][j]) {
//                    Queue<Node> queue = new LinkedList<>();
//                    queue.offer(new Node(i, j));
//                    map[i][j] = island;
//                    visited[i][j] = true;
//                    while (!queue.isEmpty()) {
//                        Node node = queue.poll();
//                        for (int d = 0; d < 4; d++) {
//                            int nr = node.r + deltas[d][0];
//                            int nc = node.c + deltas[d][1];
//                            if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == 1) {
//                                queue.offer(new Node(nr, nc));
//                                map[nr][nc] = island;
//                                visited[nr][nc] = true;
//                            }
//                        }
//                    }
//                    island++;
//                }
//            }
//        }
//    }

    private static void bfs(Node land) {
        int n = map[land.r][land.c];
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[N][N];
        queue.offer(new Node(land.r, land.c));
        visited[land.r][land.c] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] != n) {
                    if (map[nr][nc] == 0) queue.offer(new Node(nr, nc, node.d + 1));
                    else {
                        ans = Math.min(ans, node.d);
                        return;
                    }
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static class Node {
        int r, c, d;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
            this.d = 0;
        }

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2146().solution();
    }
}
