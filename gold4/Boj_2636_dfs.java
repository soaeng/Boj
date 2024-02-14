package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
    치즈
    https://www.acmicpc.net/problem/2636
*/
public class Boj_2636_dfs {
    static int R, C;
    static int[][] map;
    static boolean[][] visited;

    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Deque<Cheese> deque;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int time = 0, cheese = 0;
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        deque = new ArrayDeque<>();
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    deque.offer(new Cheese(r, c));
                }
            }
        }

        while (!deque.isEmpty()) {
            visited = new boolean[R][C];
            checkAir(0, 0);
            cheese = meltCheese();
            setAir();
            time++;
        }
        sb.append(time).append("\n").append(cheese);
        System.out.println(sb);
    }

    private static void setAir() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (Math.abs(map[r][c]) == 2) map[r][c] = 0;
            }
        }
    }

    private static void checkAir(int r, int c) {
        map[r][c] = 2;
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (isOutOfRange(nr, nc)) continue;
            if (visited[nr][nc]) continue;
            if (map[nr][nc] != 0) continue;
            checkAir(nr, nc);
        }
    }

    private static int meltCheese() {
        int size = deque.size();
        int count = 0;
        while (size-- > 0) {
            Cheese cheese = deque.poll();
            if (isContactWithAir(cheese)) {
                map[cheese.r][cheese.c] = -2;
                count++;
            } else deque.offer(cheese);
        }
        return count;
    }

    private static boolean isContactWithAir(Cheese cheese) {
        for (int d = 0; d < 4; d++) {
            int nr = cheese.r + deltas[d][0];
            int nc = cheese.c + deltas[d][1];
            if (isOutOfRange(nr, nc)) continue;
            if (map[nr][nc] == 2) return true;
        }
        return false;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    private static class Cheese {
        int r, c;

        public Cheese(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
