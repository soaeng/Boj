package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/*
    통나무 옮기기
    https://www.acmicpc.net/problem/1938
*/
public class Boj_1938 {
    static int N;
    static char[][] map;
    static boolean[][][] visited;
    static Wood end;
    static Queue<Wood> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[2][N][N];
        queue = new LinkedList<>();
        int cntB = 0, cntE = 0;
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 'B') {
                    cntB++;
                    map[r][c] = '0';
                    if (cntB == 2) {
                        int d = getDirection(r, c, 'B');
                        queue.offer(new Wood(r, c, d, 0));
                        visited[d][r][c] = true;
                    }
                }
                if (map[r][c] == 'E') {
                    cntE++;
                    map[r][c] = '0';
                    if (cntE == 2) end = new Wood(r, c, getDirection(r, c, 'E'), 0);
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int ans = 0;
        while (!queue.isEmpty()) {
            Wood wood = queue.poll();
            if (wood.equals(end)) ans = wood.t;
            moveTree(wood);
        }
        return ans;
    }

    private static boolean checkEdge(int r, int c, int d) {
        if (d == 0) {
            if (c - 1 >= 0 && map[r][c - 1] == '1') return false;
            else if (c + 1 < N && map[r][c + 1] == '1') return false;
            return c - 1 >= 0 && c + 1 < N;
        } else {
            if (r - 1 >= 0 && map[r - 1][c] == '1') return false;
            else if (r + 1 < N && map[r + 1][c] == '1') return false;
            return r - 1 >= 0 && r + 1 < N;
        }
    }

    private static void moveTree(Wood wood) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int d = 0; d < 4; d++) {
            int nr = wood.r + deltas[d][0];
            int nc = wood.c + deltas[d][1];
            if (!isInRange(nr, nc) || visited[wood.d][nr][nc]) continue;
            if (!checkEdge(nr, nc, wood.d)) continue;
            if (map[nr][nc] == '1') continue;
            queue.offer(new Wood(nr, nc, wood.d, wood.t + 1));
            visited[wood.d][nr][nc] = true;
        }
        if (canRotate(wood.r, wood.c)) {
            wood.d = wood.d == 0 ? 1 : 0;
            wood.t = wood.t + 1;
            if (!visited[wood.d][wood.r][wood.c]) {
                queue.offer(new Wood(wood));
                visited[wood.d][wood.r][wood.c] = true;
            }
        }
    }

    private static boolean canRotate(int r, int c) {
        int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int d = 0; d < 8; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (!isInRange(nr, nc)) return false;
            if (map[nr][nc] == '1') return false;
        }
        return true;
    }

    private static int getDirection(int r, int c, char ch) {
        int[][] deltas = {{0, 1}, {1, 0}};
        for (int d = 0; d < 2; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (!isInRange(nr, nc)) continue;
            if (map[nr][nc] == ch) return d;
        }
        return -1;
    }

    private static class Wood {
        // r: 행, c: 열, d: 방향(0:수평, 1: 수직)
        int r, c, d, t;

        public Wood(int r, int c, int d, int t) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.t = t;
        }

        public Wood(Wood wood) {
            this.r = wood.r;
            this.c = wood.c;
            this.d = wood.d;
            this.t = wood.t;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.r, this.c);
        }

        @Override
        public boolean equals(Object obj) {
            Wood wood = (Wood) obj;
            return Objects.equals(this.r, wood.r) && this.c == wood.c && this.d == wood.d;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + d + ")";
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    public static void main(String[] args) throws Exception {
        new Boj_1938().solution();
    }
}
