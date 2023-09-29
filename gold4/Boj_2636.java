package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    치즈
    https://www.acmicpc.net/problem/2636
*/
public class Boj_2636 {
    static int R, C;
    static int[][] map;
    static int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static boolean[][] visited;

    static ArrayList<Node> list;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        list = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) list.add(new Node(i, j));
            }
        }
        int t = 0;
        int cnt = 0;
        while (!list.isEmpty()) {
            visited = new boolean[R][C];
            t++;
            checkOutsideAir();
            cnt = meltCheese();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(t).append("\n").append(cnt);
        System.out.println(sb);
    }

    private static int meltCheese() {
        int size = list.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            Node node = list.get(i);
            if (checkMeltCheese(node.r, node.c)) {
                list.remove(node);
                map[node.r][node.c] = 0;
                i--;
                size--;
                count++;
            }
        }
        return count;
    }

    private static boolean checkMeltCheese(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (checkRange(nr, nc) && map[nr][nc] == -2) return true;
        }
        return false;
    }

    private static void checkOutsideAir() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0));
        visited[0][0] = true;
        map[0][0] = -2;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (checkRange(nr, nc) && !visited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == -2)) {
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                    map[nr][nc] = -2;
                }
            }
        }
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2636().solution();
    }
}
