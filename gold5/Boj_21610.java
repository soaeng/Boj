package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    마법사 상어와 비바라기
    https://www.acmicpc.net/problem/21610
*/
public class Boj_21610 {
    static int N, M, ans = 0;
    static int[][] map;
    static int[][] info;
    static int[][] deltas = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        info = new int[M][2];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        queue.offer(new Node(N - 1, 0));
        queue.offer(new Node(N - 1, 1));
        queue.offer(new Node(N - 2, 0));
        queue.offer(new Node(N - 2, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken()) - 1;
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            move(info[i][0], info[i][1]);
            bfs(i == M - 1);
            System.out.println();
        }

        System.out.println(ans);
    }

    private static void move(int d, int s) {
        for (Node c : queue) {
            c.r = (N + c.r + deltas[d][0] * (s % N)) % N;
            c.c = (N + c.c + deltas[d][1] * (s % N)) % N;
            map[c.r][c.c]++;
        }
    }

    private static void bfs(boolean last) {
        boolean[][] visited = new boolean[N][N];
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int count = 0;
            visited[node.r][node.c] = true;
            for (int d = 1; d < 8; d += 2) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] > 0) count++;
            }
            map[node.r][node.c] += count;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] > 1) {
                    queue.offer(new Node(i, j));
                    map[i][j] -= 2;
                }
                if (last) ans += map[i][j];
            }
        }
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    public static void main(String[] args) throws Exception {
        new Boj_21610().solution();
    }
}
