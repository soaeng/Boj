package platinum5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    온풍기 안녕!
    https://www.acmicpc.net/problem/23289
*/
public class Boj_23289 {
    /*
     * R: 행
     * C: 열
     * K: 기준 온도
     * W: 벽의 개수
     *
     * 0: 빈 칸
     * 1: 방향이 오른쪽인 온풍기가 있음
     * 2: 방향이 왼쪽인 온풍기가 있음
     * 3: 방향이 위인 온풍기가 있음
     * 4: 방향이 아래인 온풍기가 있음
     * 5: 온도를 조사해야 하는 칸
     *
     * 벽 정보: x, y, t
     * t==0? (x, y)와 (x-1, y) 사이에 벽
     * t==1? (x, y)와 (x, y+1) 사이에 벽
     */
    static int R, C, K, W;
    static int[][] map;
    static int[][][] deltas = {
            {{-1, 1}, {0, 1}, {1, 1}},
            {{-1, -1}, {0, -1}, {1, -1}},
            {{-1, -1}, {-1, 0}, {-1, 1}},
            {{1, -1}, {1, 0}, {1, 1}}
    };
    static ArrayList<Node> heaters;
    static int[][] wall;
    static ArrayList<Node> investigation;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        heaters = new ArrayList<>();
        wall = new int[R][C];
        investigation = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                switch (map[i][j]) {
                    case 0:
                        break;
                    case 5:
                        investigation.add(new Node(i, j));
                        map[i][j] = 0;
                        break;
                    default:
                        heaters.add(new Node(i, j, map[i][j] - 1));
                        map[i][j] = 0;
                        break;
                }
            }
        }
        W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            if (wall[r][c] != 0) wall[r][c] = 3;
            else wall[r][c] = t + 1;
        }

        int chocolate = 0;
        while (chocolate <= 100 && !checkComplete()) {
            operateHeater();
            controlTemperature();
            reduceTemperature();
            chocolate++;
        }
        System.out.println(chocolate);
    }

    private static void operateHeater() {
        for (Node heater : heaters) {
            int r = heater.r + deltas[heater.dir][1][0];
            int c = heater.c + deltas[heater.dir][1][1];
            Queue<Node> queue = new LinkedList<>();
            boolean[][] visited = new boolean[R][C];
            queue.offer(new Node(r, c, 5));
            visited[r][c] = true;
            map[r][c] += 5;
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.dir == 1) break;
                for (int d = 0; d < 3; d++) {
                    int nr = node.r + deltas[heater.dir][d][0];
                    int nc = node.c + deltas[heater.dir][d][1];
                    if (checkRange(nr, nc) && !visited[nr][nc]) {
                        if (checkSpread(nr, nc, heater.dir, d)) {
                            queue.offer(new Node(nr, nc, node.dir - 1));
                            visited[nr][nc] = true;
                            map[nr][nc] += node.dir - 1;
                        }
                    }
                }
            }
        }
    }

    private static void controlTemperature() {
        int[][] temp = new int[R][C];
        boolean[][] visited = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                visited[r][c] = true;
                for (int d = 0; d < 4; d++) {
                    int nr = r + deltas[d][1][0];
                    int nc = c + deltas[d][1][1];
                    if (checkRange(nr, nc) && !visited[nr][nc] && checkSpread(nr, nc, d, 1)) {
                        int diff = Math.abs((map[r][c] - map[nr][nc]) / 4);
                        if (map[r][c] > map[nr][nc]) {
                            temp[r][c] -= diff;
                            temp[nr][nc] += diff;
                        } else {
                            temp[r][c] += diff;
                            temp[nr][nc] -= diff;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += temp[i][j];
            }
        }
    }

    private static void reduceTemperature() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i == 0 || i == R - 1 || j == 0 || j == C - 1) {
                    map[i][j] = Math.max(map[i][j] - 1, 0);
                }
            }
        }
    }

    private static boolean checkComplete() {
        for (Node node : investigation) {
            if (map[node.r][node.c] < K) return false;
        }
        return true;
    }

    private static boolean checkSpread(int r, int c, int dir, int d) {
        switch (dir) {
            case 0:
                if (d == 0) {
                    if (wall[r][c - 1] >= 2 || wall[r + 1][c - 1] == 1 || wall[r + 1][c - 1] == 3) return false;
                } else if (d == 1) {
                    if (wall[r][c - 1] >= 2) return false;
                } else {
                    if (wall[r][c - 1] > 0) return false;
                }
                break;
            case 1:
                if (d == 0) {
                    if (wall[r][c] >= 2 || wall[r + 1][c + 1] == 1 || wall[r + 1][c + 1] == 3) return false;
                } else if (d == 1) {
                    if (wall[r][c] >= 2) return false;
                } else {
                    if (wall[r][c] >= 2 || wall[r][c + 1] == 1 || wall[r][c + 1] == 3) return false;
                }
                break;
            case 2:
                if (d == 0) {
                    if (wall[r + 1][c] > 0) return false;
                } else if (d == 1) {
                    if (wall[r + 1][c] == 1 || wall[r + 1][c] == 3) return false;
                } else {
                    if (wall[r + 1][c] == 1 || wall[r + 1][c] == 3 || wall[r + 1][c - 1] >= 2) return false;
                }
                break;
            case 3:
                if (d == 0) {
                    if (wall[r][c] == 1 || wall[r][c] == 3 || wall[r - 1][c] >= 2) return false;
                } else if (d == 1) {
                    if (wall[r][c] == 1 || wall[r][c] == 3) return false;
                } else {
                    if (wall[r][c] == 1 || wall[r][c] == 3 || wall[r - 1][c - 1] >= 2) return false;
                }
                break;
        }
        return true;
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static class Node {
        int r, c, dir;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Node(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_23289().solution();
    }
}
