package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    마법사 상어와 비바라기
    https://www.acmicpc.net/problem/21610
*/
public class Boj_21610_2 {
    static int N;
    static int[][] grid;
    static int[][] deltas = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static Queue<Node> clouds;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        clouds = new ArrayDeque<>();

        // 격자 정보 입력
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 비구름 생성
        for (int i = 1; i <= 2; i++) {
            for (int j = 0; j < 2; j++) {
                clouds.offer(new Node(N - i, j));
            }
        }

        // 마법 시전
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            // 구름 이동
            moveClouds(d, s);
        }
        System.out.println(calculate());
    }

    private static void moveClouds(int d, int s) {
        for (Node node : clouds) {
            // 모든 구름이 d 방향으로 s칸 이동
            node.r = (node.r + deltas[d][0] * (s % N) + N) % N;
            node.c = (node.c + deltas[d][1] * (s % N) + N) % N;
            // 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
            grid[node.r][node.c]++;
        }
        createClouds();
    }

    private static void createClouds() {
        boolean[][] isClouds = new boolean[N][N];
        while (!clouds.isEmpty()) {
            Node node = clouds.poll();
            // 대각선 방향으로 거리가 1인 칸에
            // 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가
            for (int d = 1; d < 8; d += 2) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (grid[nr][nc] == 0) continue;
                grid[node.r][node.c]++;
            }
            isClouds[node.r][node.c] = true;
        }
        // 물의 양이 2 이상인 모든 칸에 구름이 생기고,물의 양이 2 줄어든다.
        // 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (isClouds[r][c]) continue;
                if (grid[r][c] < 2) continue;
                clouds.offer(new Node(r, c));
                grid[r][c] -= 2;
            }
        }
    }

    private static int calculate() {
        int sum = 0;
        for (int[] gg : grid) {
            for (int g : gg) sum += g;
        }
        return sum;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
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
