package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    치즈
    https://www.acmicpc.net/problem/2636
*/
public class Boj_2636_bfs {
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int R, C;
    static boolean[][] state, air;

    static ArrayList<Node> cheese;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        state = new boolean[R][C];
        air = new boolean[R][C];
        cheese = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                if (st.nextToken().equals("1")) {
                    state[i][j] = true;
                    cheese.add(new Node(i, j));
                }
            }
        }
        int time = 0, count;
        do {
            time++;
            count = cheese.size();
            setAir();
            melt();
        } while (!cheese.isEmpty());
        sb.append(time).append("\n").append(count);
        System.out.println(sb);
    }

    private static void setAir() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        air = new boolean[R][C];
        queue.offer(new Node(0, 0));
        visited[0][0] = true;
        air[0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc) || visited[nr][nc]) continue;
                if (state[nr][nc]) continue;
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = true;
                air[nr][nc] = true;
            }
        }
    }

    private static void melt() {
        ArrayList<Node> temp = new ArrayList<>();
        for (Node node : cheese) {
            if (contactAir(node)) state[node.r][node.c] = false;
            else temp.add(node);
        }
        cheese = temp;
    }

    private static boolean contactAir(Node node) {
        for (int d = 0; d < 4; d++) {
            int nr = node.r + deltas[d][0];
            int nc = node.c + deltas[d][1];
            if (air[nr][nc]) return true;
        }
        return false;
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
        new Boj_2636_bfs().solution();
    }
}
