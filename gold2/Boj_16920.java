package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    확장 게임
    https://www.acmicpc.net/problem/16920
*/
public class Boj_16920 {
    static int N, M, P;
    static char[][] map;
    static int[] players;
    static int[] ans;
    static Queue<Node>[] queues;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        players = new int[P + 1];
        queues = new LinkedList[P + 1];
        ans = new int[P + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            players[i] = Integer.parseInt(st.nextToken());
            queues[i] = new LinkedList<>();
        }
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] > '0' && map[i][j] <= '9') {
                    queues[map[i][j] - '0'].add(new Node(i, j));
                    ans[map[i][j] - '0']++;
                }
            }
        }
        bfs();
        StringBuilder sb = new StringBuilder();
        for (int p = 1; p <= P; p++) {
            sb.append(ans[p]).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (true) {
            boolean flag = true;
            for (int i = 1; i <= P; i++) {
                if (!queues[i].isEmpty()) {
                    flag = false;
                    break;
                }
            }
            if (flag) break;

            // 플레이어 번호
            for (int p = 1; p <= P; p++) {
                // 이동 가능 거리
                for (int i = 0; i < players[p]; i++) {
                    if (queues[p].isEmpty()) break;
                    int size = queues[p].size();
                    for (int j = 0; j < size; j++) {
                        Node node = queues[p].poll();
                        for (int d = 0; d < 4; d++) {
                            int nr = node.r + deltas[d][0];
                            int nc = node.c + deltas[d][1];
                            if (outOfRange(nr, nc)) continue;
                            if (map[nr][nc] != '.') continue;
                            map[nr][nc] = map[node.r][node.c];
                            queues[p].offer(new Node(nr, nc));
                            ans[p]++;
                        }
                    }
                }
            }
        }
    }

    private static boolean outOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
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
        new Boj_16920().solution();
    }
}
