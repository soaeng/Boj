package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    경쟁적 전염
    https://www.acmicpc.net/problem/18405
*/
public class Boj_18405 {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        PriorityQueue<Node> virus = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    virus.offer(new Node(i, j, map[i][j], 0));
                    visited[i][j] = true;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int targetR = Integer.parseInt(st.nextToken()) - 1;
        int targetC = Integer.parseInt(st.nextToken()) - 1;
        while (!virus.isEmpty() && S-- > 0) {
            int size = virus.size();
            for (int s = 0; s < size; s++) {
                Node node = virus.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (!isInRange(nr, nc, N)) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] != 0) continue;
                    virus.offer(new Node(nr, nc, map[node.r][node.c], node.t + 1));
                    map[nr][nc] = map[node.r][node.c];
                    visited[nr][nc] = true;
                }
            }
        }
        System.out.println(map[targetR][targetC]);
    }

    private static boolean isInRange(int r, int c, int N) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Node implements Comparable<Node> {
        int r, c, n, t;

        public Node(int r, int c, int n, int t) {
            this.r = r;
            this.c = c;
            this.n = n;
            this.t = t;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }

        @Override
        public int compareTo(Node o) {
            if (this.t == o.t) return this.n - o.n;
            return this.t - o.t;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_18405().solution();
    }
}
