package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    연구소
    https://www.acmicpc.net/problem/14502
*/
public class Boj_14502 {
    static int N, M, MAX = 0;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Node> virus = new ArrayDeque<>();
    static ArrayList<Node> blank = new ArrayList<>();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) blank.add(new Node(i, j));
                else if (map[i][j] == 2) virus.add(new Node(i, j));
            }
        }
        buildWall(0, 0);
        System.out.println(MAX);
    }

    private static void buildWall(int start, int count) {
        if (count == 3) {
            bfs();
            return;
        }
        for (int i = start; i < blank.size(); i++) {
            Node node = blank.get(i);
            if (map[node.r][node.c] == 0) {
                map[node.r][node.c] = 1;
                buildWall(i + 1, count + 1);
                map[node.r][node.c] = 0;
            }
        }
    }

    private static void bfs() {
        int[][] lab = new int[N][M];
        for (int i = 0; i < N; i++) {
            lab[i] = map[i].clone();
        }

        Queue<Node> queue = new ArrayDeque<>(virus);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (lab[nr][nc] != 0) continue;
                queue.offer(new Node(nr, nc));
                lab[nr][nc] = 2;
            }
        }
        getSafeZone(lab);
    }

    private static void getSafeZone(int[][] map) {
        int count = 0;
        for (int[] nn : map) {
            for (int n : nn) {
                if (n == 0) count++;
            }
        }
        if (MAX < count) MAX = count;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_14502().solution();
    }
}
