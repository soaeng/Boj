package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    소년 점프
    https://www.acmicpc.net/problem/16469
*/
public class Boj_16469 {
    static int R, C;
    static boolean[][] wall;
    static int[][][] visited;
    static Queue<Node>[] queues;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        wall = new boolean[R][C];
        visited = new int[3][R][C];
        queues = new Queue[3];
        for (int r = 0; r < R; r++) {
            String input = br.readLine();
            for (int c = 0; c < C; c++) {
                if (input.charAt(c) == '1') wall[r][c] = true;
            }
        }
        for (int i = 0; i < 3; i++) {
            queues[i] = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            queues[i].offer(new Node(r, c));
            visited[i][r][c] = 1;
        }
        bfs();
        System.out.println(getTime());
    }

    private static String getTime() {
        StringBuilder sb = new StringBuilder();
        int time = Integer.MAX_VALUE;
        int count = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (wall[r][c]) continue;
                if (visited[0][r][c] == 0 || visited[1][r][c] == 0 || visited[2][r][c] == 0) continue;
                int temp = Math.max(visited[0][r][c], Math.max(visited[1][r][c], visited[2][r][c]));
                if (time > temp) {
                    time = temp;
                    count = 1;
                } else if (time == temp) count++;
            }
        }
        if (time == Integer.MAX_VALUE) sb.append(-1);
        else {
            sb.append(time - 1).append("\n").append(count);
        }
        return sb.toString();
    }

    private static void bfs() {
        while (isNotEmpty()) {
            for (int id = 0; id < 3; id++) {
                int size = queues[id].size();
                for (int s = 0; s < size; s++) {
                    Node node = queues[id].poll();
                    for (int d = 0; d < 4; d++) {
                        int nr = node.r + deltas[d][0];
                        int nc = node.c + deltas[d][1];
                        if (isOutOfRange(nr, nc)) continue;
                        if (visited[id][nr][nc] != 0) continue;
                        if (wall[nr][nc]) continue;
                        queues[id].offer(new Node(nr, nc));
                        visited[id][nr][nc] = visited[id][node.r][node.c] + 1;
                    }
                }
            }
        }
    }

    private static boolean isNotEmpty() {
        if (queues[0].isEmpty()) return false;
        if (queues[1].isEmpty()) return false;
        return !queues[2].isEmpty();
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
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
