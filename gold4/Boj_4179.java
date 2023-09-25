package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    불!
    https://www.acmicpc.net/problem/4179
*/
public class Boj_4179 {
    static int R, C, jr, jc, ans;
    static char[][] map;
    static Queue<Node> fire;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fire = new LinkedList<>();
        ans = 0;
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                switch (map[i][j]) {
                    case 'J':
                        jr = i;
                        jc = j;
                        break;
                    case 'F':
                        fire.add(new Node(i, j, 0));
                        break;
                }
            }
        }
        bfs();
        if (ans == 0) System.out.println("IMPOSSIBLE");
        else System.out.println(ans);
    }

    private static void bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(jr, jc, 0));
        while (!queue.isEmpty()) {
            int size = fire.size();
            for (int i = 0; i < size; i++) {
                Node node = fire.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (checkRange(nr, nc) && map[nr][nc] != 'F' && map[nr][nc] != '#') {
                        map[nr][nc] = 'F';
                        fire.add(new Node(nr, nc, node.t + 1));
                    }
                }
            }
            size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (!checkRange(nr, nc)) {
                        ans = node.t + 1;
                        return;
                    }
                    if (map[nr][nc] == '.') {
                        map[nr][nc] = 'J';
                        queue.offer(new Node(nr, nc, node.t + 1));
                    }
                }
            }
        }
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static class Node {
        int r, c, t;

        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_4179().solution();
    }
}
