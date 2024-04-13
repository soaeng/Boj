package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    달이 차오른다, 가자
    https://www.acmicpc.net/problem/1194
*/
public class Boj_1194_2 {
    static int N, M;
    static char[][] map;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        Node start = null;
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < M && start == null; c++) {
                if (map[r][c] == '0') start = new Node(r, c, 0);
            }
        }
        System.out.println(bfs(start));
    }

    private static int bfs(Node start) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][][] visited = new boolean[64][N][M];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[0][start.r][start.c] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (map[node.r][node.c] == '1') return time;
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[node.key][nr][nc]) continue;
                    // 벽: 절대 이동할 수 없다. ('#')
                    if (map[nr][nc] == '#') continue;
                    int key = node.key;
                    // 열쇠
                    if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
                        // or 연산으로 얻은 열쇠 체크
                        key = node.key | 1 << (map[nr][nc] - 'a');
                    }
                    // 문
                    else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
                        // 대응하는 열쇠가 없으면 pass
                        // and 연산으로 0이라면 열쇠 없는거
                        if ((key & 1 << (map[nr][nc] - 'A')) == 0) continue;
                    }
                    queue.offer(new Node(nr, nc, key));
                    visited[key][nr][nc] = true;
                }
            }
            time++;
        }
        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node {
        int r, c, key;

        public Node(int r, int c, int key) {
            this.r = r;
            this.c = c;
            this.key = key;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + Integer.toBinaryString(key) + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
