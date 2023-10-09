package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    달이 차오른다, 가자.
    https://www.acmicpc.net/problem/1194
*/
public class Boj_1194 {
    static int N, M;
    static char[][] map;
    static boolean[][][] visited;
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[64][N][M];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') {
                    queue.offer(new Node(i, j, 0, 0));
                    visited[0][i][j] = true;
                }
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (map[node.r][node.c] == '1') return node.d;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (checkRange(nr, nc) && !visited[node.keys][nr][nc] && map[nr][nc] != '#') {
                    if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
                        int nk = getKey(nr, nc, node.keys);
                        queue.offer(new Node(nr, nc, node.d + 1, nk));
                        visited[nk][nr][nc] = true;
                    } else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
                        if (canOpen(nr, nc, node.keys)) {
                            queue.offer(new Node(nr, nc, node.d + 1, node.keys));
                            visited[node.keys][nr][nc] = true;
                        }
                    } else {
                        queue.offer(new Node(nr, nc, node.d + 1, node.keys));
                        visited[node.keys][nr][nc] = true;
                    }
                }
            }
        }
        return -1;
    }

    private static boolean canOpen(int r, int c, int keys) {
        return (keys & 1 << (map[r][c] - 'A')) == (int) Math.pow(2, map[r][c] - 'A');
    }

    private static int getKey(int r, int c, int keys) {
        int nk = 1 << (map[r][c] - 'a');
        return nk | keys;
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Node {
        int r, c, d, keys;

        public Node(int r, int c, int d, int keys) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.keys = keys;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", d=" + d +
                    ", keys=" + keys +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_1194().solution();
    }
}

