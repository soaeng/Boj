package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    점프 게임
    https://www.acmicpc.net/problem/15558
*/
public class Boj_15558 {
    static int N, k;
    static boolean[][] grid;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        grid = new boolean[2][N + k + 1];
        for (int i = 0; i < 2; i++) {
            String input = br.readLine();
            for (int n = 0; n < N; n++) {
                if (input.charAt(n) == '1') grid[i][n] = true;
            }
            Arrays.fill(grid[i], N, N + k + 1, true);
        }
        System.out.println(bfs() ? 1 : 0);
    }

    private static boolean bfs() {
        int[] deltas = {-1, 1, k};
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Node node = queue.poll();
                if (node.pos >= N) return true;
                for (int d = 0; d < 3; d++) {
                    int nd = node.dir;
                    int np = node.pos + deltas[d];
                    if (d == 2) nd = node.dir == 0 ? 1 : 0;
                    if (np - 1 < time) continue;
                    if (!grid[nd][np]) continue;
                    queue.offer(new Node(nd, np));
                    grid[nd][np] = false;
                }
            }
            time++;
        }
        return false;
    }

    private static class Node {
        int dir, pos;

        public Node(int dir, int pos) {
            this.dir = dir;
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
