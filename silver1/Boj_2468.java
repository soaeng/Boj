package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    안전 영역
    https://www.acmicpc.net/problem/2468
    채점 정보
    bfs(55412KB, 312ms)
    dfs(19588KB, 272ms)
*/
public class Boj_2468 {
    static int N, MIN = 101, MAX = 0, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (MAX < map[i][j]) MAX = map[i][j];
                if (MIN > map[i][j]) MIN = map[i][j];
            }
        }
        getSafetyArea();
        System.out.println(ans);
    }

    static void getSafetyArea() {
        for (int h = MIN - 1; h <= MAX; h++) {
            visited = new boolean[N][N];
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= h) visited[i][j] = true;
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        count++;
//                        bfs(i, j);
                        dfs(i, j, h);
                    }
                }
            }
            if (ans < count) ans = count;
        }
    }

    static void dfs(int r, int c, int h) {
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] > h) {
                dfs(nr, nc, h);
            }
        }
    }

    static void bfs(int r, int c) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(r, c));
        visited[r][c] = true;
        Node node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2468().solution();
    }
}
