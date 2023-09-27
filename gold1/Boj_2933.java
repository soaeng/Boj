package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    미네랄
    https://www.acmicpc.net/problem/2933
*/
public class Boj_2933 {
    static int R, C, h;
    static char[][] map;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> heights = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights.add(Integer.parseInt(st.nextToken()) - 1);
        }
        for (int i = 0; i < N; i++) {
            h = R - heights.poll() - 1;
            if (throwStick(h, i % 2)) getClusters();
        }
        System.out.println(printMap());
    }

    private static boolean throwStick(int h, int dir) {
        int[] deltas = {1, -1};
        boolean flag = false;
        for (int i = -1; i <= C; i++) {

            int c = dir == 0 ? i + deltas[dir] : C - i - 1 + deltas[dir];
            if (checkColumnRange(c)) {
                if (map[h][c] == 'x') {
                    map[h][c] = '.';
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    private static void getClusters() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < C; i++) {
            if (map[R - 1][i] == 'x') {
                queue.offer(new Node(R - 1, i));
                visited[R - 1][i] = true;
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (checkRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 'x') {
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        ArrayList<Node> cluster = findInTheAir(visited);
        if (!cluster.isEmpty()) workByGravity(cluster);
    }

    private static ArrayList<Node> findInTheAir(boolean[][] visited) {
        ArrayList<Node> list = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && map[i][j] == 'x') {
                    list.add(new Node(i, j));
                    map[i][j] = '.';
                    visited[i][j] = true;
                }
            }
        }
        return list;
    }

    private static void workByGravity(ArrayList<Node> cluster) {
        int size = cluster.size();
        boolean flag = false;
        while (!flag) {
            for (Node value : cluster) {
                int r = value.r + 1;
                int c = value.c;
                if (r >= R || map[r][c] == 'x') {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                for (Node node : cluster) {
                    node.r++;
                }
            }
        }
        for (Node node : cluster) {
            map[node.r][node.c] = 'x';
        }
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static boolean checkColumnRange(int c) {
        return c >= 0 && c < C;
    }

    private static String printMap() {
        StringBuilder sb = new StringBuilder();
        for (char[] cc : map) {
            for (char c : cc) {
                sb.append(c);
            }
            sb.append("\n");
        }
        return sb.toString();
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
        new Boj_2933().solution();
    }
}
