package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    연구소 3
    https://www.acmicpc.net/problem/17142
*/
public class Boj_17142_2 {
    static int N, M, min, blank;
    static int[][] map;
    static ArrayList<Node> virus;
    static Node[] comb;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virus = new ArrayList<>();
        comb = new Node[M];
        min = Integer.MAX_VALUE;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 0) blank++;
                else if (map[r][c] == 2) virus.add(new Node(r, c));
            }
        }
        if (blank == 0) {
            System.out.println(0);
            return;
        }
        dfs(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void dfs(int depth, int start) {
        if (depth == M) {
            min = Math.min(min, bfs());
            return;
        }
        for (int i = start; i < virus.size(); i++) {
            comb[depth] = virus.get(i);
            dfs(depth + 1, i + 1);
        }
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        for (Node node : comb) {
            queue.offer(node);
            visited[node.r][node.c] = true;
        }
        int time = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] == 1) continue;
                    if (map[nr][nc] == 0) count++;
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
            time++;
            if (blank == count) return time;
        }
        return Integer.MAX_VALUE;
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
