package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    나이트의 이동
    https://www.acmicpc.net/problem/7562
*/
public class Boj_7562 {
    static int T, N;
    static int[][] map;
    static int[][] deltas = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    static Queue<Node> queue;
    static Node start, goal;
    static StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            goal = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            bfs();
        }
        System.out.println(sb);
    }

    private static void bfs() {
        queue = new LinkedList<>();
        queue.offer(start);
        map[start.r][start.c] = 1;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.r == goal.r && node.c == goal.c) {
                sb.append(map[goal.r][goal.c] - 1).append("\n");
                break;
            }
            for (int d = 0; d < 8; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 0) {
                    map[nr][nc] = map[node.r][node.c] + 1;
                    queue.offer(new Node(nr, nc));
                }
            }
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
        new Boj_7562().solution();
    }
}
