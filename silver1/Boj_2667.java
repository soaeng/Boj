package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    단지 번호 붙이기
    https://www.acmicpc.net/problem/2667
*/
public class Boj_2667 {
    static int N, count;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;
    static Queue<Node> queue;
    static ArrayList<Integer> list = new ArrayList<>();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == '1') {
//                    bfs(i, j);
                    count = 1;
                    dfs(i, j);
                    list.add(count);
                }
            }
        }
        Collections.sort(list);
        sb.append(list.size()).append("\n");
        for (int l : list) {
            sb.append(l).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int r, int c) {
        if (visited[r][c]) return;
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == '1') {
                dfs(nr, nc);
                count++;
            }
        }
    }

    static void bfs(int r, int c) {
        queue = new LinkedList<>();
        queue.offer(new Node(r, c));
        visited[r][c] = true;
        Node node;
        int count = 0;
        while (!queue.isEmpty()) {
            node = queue.poll();
            count++;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == '1') {
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        list.add(count);
    }

    static class Node {
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
        new Boj_2667().solution();
    }
}
