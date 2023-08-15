package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    그림
    https://www.acmicpc.net/problem/1926
    [bfs] 45116 KB / 444 ms
    [dfs] 51992 KB / 356 ms
*/
public class Boj_1926 {
    static int n, m, count = 0, max = 0, area = 0;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = -1;
                    area = 0;
                    dfs(i, j);
                    count++;
//                    bfs(i, j);
//                    count++;
                }
            }
        }
        sb.append(count).append("\n").append(max);
        System.out.println(sb);
    }

//    private static void bfs(int i, int j) {
//        queue = new LinkedList<>();
//        queue.offer(new Node(i, j));
//        map[i][j] = -1;
//        count++;
//        int area = 0;
//        while (!queue.isEmpty()) {
//            Node node = queue.poll();
//            area++;
//            for (int d = 0; d < 4; d++) {
//                int nr = node.i + deltas[d][0];
//                int nc = node.j + deltas[d][1];
//                if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 1) {
//                    queue.offer(new Node(nr, nc));
//                    map[nr][nc] = -1;
//                }
//            }
//        }
//        if (max < area) max = area;
//    }

    private static void dfs(int i, int j) {
        area++;
        if (max < area) max = area;
        for (int d = 0; d < 4; d++) {
            int nr = i + deltas[d][0];
            int nc = j + deltas[d][1];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 1) {
                map[nr][nc] = -1;
                dfs(nr, nc);
            }
        }
    }

    private static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_1926().solution();
    }
}
