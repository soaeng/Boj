package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    연구소 3
    https://www.acmicpc.net/problem/17142
*/
public class Boj_17142 {
    static int N, M, ans, count;
    static int[][] map;
    static ArrayList<Node> virus;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        count = 0;
        map = new int[N][N];
        virus = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 2) virus.add(new Node(r, c, 0));
                if (map[r][c] == 0) count++;
            }
        }
        if (count == 0) {
            System.out.println(0);
            System.exit(0);
        }
        dfs(new int[M], new boolean[virus.size()], 0, virus.size(), 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans + 1);
    }

    private static void dfs(int[] comb, boolean[] selected, int start, int end, int cnt) {
        if (cnt == M) {
            int time = bfs(comb, count);
            ans = Math.min(ans, time == -1 ? Integer.MAX_VALUE : time);
            return;
        }
        for (int i = start; i < end; i++) {
            if (!selected[i]) {
                selected[i] = true;
                comb[cnt] = i;
                dfs(comb, selected, i + 1, end, cnt + 1);
                selected[i] = false;
            }
        }
    }

    private static int bfs(int[] comb, int count) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[N][N];
        Queue<Node> queue = new LinkedList<>();
        for (int idx : comb) {
            Node node = virus.get(idx);
            queue.offer(node);
            visited[node.r][node.c] = true;
        }
        int time = -1;
        while (!queue.isEmpty()) {
            time++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (!isInRange(nr, nc) || visited[nr][nc]) continue;
                    switch (map[nr][nc]) {
                        case 0:
                            count--;
                            queue.offer(new Node(nr, nc, node.t + 1));
                            visited[nr][nc] = true;
                            break;
                        case 1:
                            continue;
                        case 2:
                            queue.offer(new Node(nr, nc, node.t));
                            visited[nr][nc] = true;
                            break;
                    }
                }
            }
            if (count == 0) return time;
        }
        return -1;
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Node {
        int r, c, t;

        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_17142().solution();
    }
}
