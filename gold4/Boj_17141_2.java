package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    연구소 2
    https://www.acmicpc.net/problem/17141
*/
public class Boj_17141_2 {
    static int N, M, min, blank;
    static boolean[][] map;
    static ArrayList<Node> virus;
    static Node[] comb;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][N];
        virus = new ArrayList<>();
        min = Integer.MAX_VALUE;
        comb = new Node[M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                switch (st.nextToken()) {
                    case "0":
                        blank++;
                        break;
                    case "1":
                        map[r][c] = true;
                        break;
                    case "2":
                        blank++;
                        virus.add(new Node(r, c));
                        break;
                }
            }
        }
        combination(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void combination(int depth, int start) {
        if (depth == M) {
            min = Math.min(min, bfs());
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            comb[depth] = virus.get(i);
            combination(depth + 1, i + 1);
        }
    }

    private static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        int time = -1;
        int count = 0;
        for (Node node : comb) {
            queue.offer(node);
            visited[node.r][node.c] = true;
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                count++;
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc]) continue;
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
            time++;
        }
        return blank - count == 0 ? time : Integer.MAX_VALUE;
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
