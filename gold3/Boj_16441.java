package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    아기돼지와 늑대
    https://www.acmicpc.net/problem/16441
*/
public class Boj_16441 {
    static int N, M;
    static char[][] map;
    static ArrayList<Node> wolves;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        wolves = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 'W') {
                    wolves.add(new Node(r, c));
                } else if (map[r][c] == '.') map[r][c] = 'P';
            }
        }
        bfs();
        StringBuilder sb = new StringBuilder();
        for (char[] mm : map) {
            for (char m : mm) {
                sb.append(m);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        for (Node wolf : wolves) {
            queue.offer(wolf);
            visited[wolf.r][wolf.c] = true;
        }
        while (!queue.isEmpty()) {
            Node wolf = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = wolf.r + deltas[d][0];
                int nc = wolf.c + deltas[d][1];
                if (visited[nr][nc]) continue;
                switch (map[nr][nc]) {
                    case '#':
                        break;
                    case 'P':
                        queue.offer(new Node(nr, nc));
                        map[nr][nc] = '.';
                        visited[nr][nc] = true;
                        break;
                    case '+':
                        while (map[nr][nc] == '+' && map[nr + deltas[d][0]][nc + deltas[d][1]] != '#') {
                            nr += deltas[d][0];
                            nc += deltas[d][1];
                        }
                        if (!visited[nr][nc]) {
                            queue.offer(new Node(nr, nc));
                            visited[nr][nc] = true;
                            if (map[nr][nc] == 'P') map[nr][nc] = '.';
                        }
                        break;
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
        new Boj_16441().solution();
    }
}
