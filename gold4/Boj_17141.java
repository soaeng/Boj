package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    연구소 2
    https://www.acmicpc.net/problem/17141
*/
public class Boj_17141 {
    static int N, M, min;
    static int[][] map;
    static ArrayList<Node> virus;
    static Node[] comb;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virus = new ArrayList<>();
        comb = new Node[M];
        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0, idx = 0; j < N; j++, idx += 2) {
                map[i][j] = Character.getNumericValue(input.charAt(idx));
                if (map[i][j] == 2) {
                    virus.add(new Node(i, j));
                    map[i][j] = 0;
                }
            }
        }
        dfs(0, 0, virus.size());
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static int[][] copyOfMap() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }
        return temp;
    }

    private static void bfs(Node[] comb) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] temp = copyOfMap();
        int time = -1;
        Queue<Node> queue = new ArrayDeque<>();

        for (Node node : comb) {
            temp[node.r][node.c] = 2;
            queue.offer(new Node(node.r, node.c));
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (time > min) break;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (temp[nr][nc] != 0) continue;
                    queue.offer(new Node(nr, nc));
                    temp[nr][nc] = 2;
                }
            }
            time++;
        }
        if (complete(temp)) min = Math.min(min, time);
    }

    private static boolean complete(int[][] map) {
        for (int[] nn : map) {
            for (int n : nn) {
                if (n == 0) return false;
            }
        }
        return true;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static void dfs(int cnt, int start, int end) {
        if (cnt == M) {
            bfs(comb);
            return;
        }
        for (int i = start; i < end; i++) {
            comb[cnt] = virus.get(i);
            dfs(cnt + 1, i + 1, end);
        }
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_17141().solution();
    }
}
