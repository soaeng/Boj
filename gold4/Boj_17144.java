package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    미세먼지 안녕!
    https://www.acmicpc.net/problem/17144
*/
public class Boj_17144 {
    static int R, C, cleaner;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Node> dusts;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        dusts = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1 && cleaner == 0) cleaner = i;
            }
        }

        for (int i = 0; i < T; i++) {
            findDust();
            spreadDust();
            operateAirCleaner();
        }
        System.out.println(calcAmountOfDust());
    }

    private static void findDust() {
        dusts = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) dusts.offer(new Node(i, j));
            }
        }
    }

    private static void spreadDust() {
        int[][] temp = new int[R][C];
        while (!dusts.isEmpty()) {
            Node node = dusts.poll();
            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (checkRange(nr, nc) && !checkAirCleaner(nr, nc)) {
                    temp[nr][nc] += calcDust(node);
                    count++;
                }
            }
            temp[node.r][node.c] -= (calcDust(node) * count);
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += temp[i][j];
            }
        }
    }

    private static void operateAirCleaner() {
        int top = cleaner;
        int bottom = cleaner + 1;
        // 위쪽 공기청정기 순환
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        if (C - 1 >= 0) System.arraycopy(map[0], 1, map[0], 0, C - 1);
//        for (int i = 0; i < C - 1; i++) {
//            map[0][i] = map[0][i + 1];
//        }
        for (int i = 0; i < top; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        if (C - 1 - 1 >= 0) System.arraycopy(map[top], 1, map[top], 2, C - 1 - 1);
//        for (int i = C - 1; i > 1; i--) {
//            map[top][i] = map[top][i - 1];
//        }
        map[top][1] = 0;

        // 아래쪽 공기청정기 순환
        for (int i = bottom + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        if (C - 1 >= 0) System.arraycopy(map[R - 1], 1, map[R - 1], 0, C - 1);
//        for (int i = 0; i < C - 1; i++) {
//            map[R - 1][i] = map[R - 1][i + 1];
//        }
        for (int i = R - 1; i > bottom; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        if (C - 1 - 1 >= 0) System.arraycopy(map[bottom], 1, map[bottom], 2, C - 1 - 1);
//        for (int i = C - 1; i > 1; i--) {
//            map[bottom][i] = map[bottom][i - 1];
//        }

        map[bottom][1] = 0;
    }

    private static int calcAmountOfDust() {
        int total = 2;
        for (int[] nn : map) {
            for (int n : nn) total += n;
        }
        return total;
    }

    private static int calcDust(Node node) {
        return map[node.r][node.c] / 5;
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static boolean checkAirCleaner(int r, int c) {
        return (r == cleaner && c == 0) || (r == cleaner + 1 && c == 0);
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_17144().solution();
    }
}
