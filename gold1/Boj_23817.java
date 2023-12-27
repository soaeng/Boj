package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    포항항
    https://www.acmicpc.net/problem/23817
*/
public class Boj_23817 {
    static int N, M, count, ans;
    static int[][] map;
    static int[][] distance;
    static ArrayList<Node> nodes;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        nodes = new ArrayList<>();
        nodes.add(null);
        int seq = 2;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                switch (str.charAt(j)) {
                    case 'S':
                        map[i][j] = 1;
                        nodes.add(1, new Node(i, j, 0));
                        break;
                    case 'K':
                        map[i][j] = seq++;
                        nodes.add(new Node(i, j, 0));
                        break;
                    case 'X':
                        map[i][j] = -1;
                        break;
                }
            }
        }
        count = nodes.size();
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }
        ans = Integer.MAX_VALUE;
        distance = new int[count][count];
        bfs();
//        for (int i = 1; i < count; i++) {
//            for (int j = 1; j < count; j++) {
//                System.out.print(distance[i][j] + " ");
//            }
//            System.out.println();
//        }
        dfs(new boolean[count], 1, 0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void dfs(boolean[] selected, int idx, int cnt, int sum) {
        if (cnt == 5) {
            ans = Math.min(ans, sum);
            return;
        }
        if (ans <= sum) return;
        for (int i = 2; i < count; i++) {
            if (distance[idx][i] == 0) continue;
            if (selected[i]) continue;
            selected[i] = true;
            dfs(selected, i, cnt + 1, sum + distance[idx][i]);
            selected[i] = false;
        }
    }

    private static void bfs() {
        for (int i = 1; i < count; i++) {
            Node start = nodes.get(i);
            int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            boolean[][] visited = new boolean[N][M];
            Queue<Node> queue = new LinkedList<>();
            queue.offer(start);
            visited[start.r][start.c] = true;
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (map[node.r][node.c] >= 1) {
                    distance[map[start.r][start.c]][map[node.r][node.c]] = node.t;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] == -1) continue;
                    visited[nr][nc] = true;
                    queue.offer(new Node(nr, nc, node.t + 1));
                }
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node {
        int seq, r, c, t;

        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.r, this.c);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) return false;
            Node node = (Node) obj;
            return Objects.equals(this.r, node.r) && this.c == node.c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + t + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_23817().solution();
    }
}
