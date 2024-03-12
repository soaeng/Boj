package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    뱀
    https://www.acmicpc.net/problem/3190
*/
public class Boj_3190_2 {
    static boolean[][] apple;
    static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static Queue<int[]> directions;
    static Deque<Node> snake;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        apple = new boolean[N][N];
        directions = new ArrayDeque<>();
        snake = new ArrayDeque<>();
        N = Integer.parseInt(br.readLine());
        for (int k = 0; k < N; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            apple[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }
        N = Integer.parseInt(br.readLine());
        for (int l = 0; l < N; l++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            directions.add(new int[]{Integer.parseInt(st.nextToken()), st.nextToken().equals("D") ? 1 : 3});
        }
        move(0, 0, 0, 0);
    }

    private static void move(int r, int c, int d, int t) {
        if (!directions.isEmpty() && directions.peek()[0] == t) {
            d = (d + directions.poll()[1]) % 4;
        }
        snake.offerFirst(new Node(r, c));
        int nr = r + deltas[d][0];
        int nc = c + deltas[d][1];
        if (isOutOfRange(nr, nc)) {
            System.out.println(t + 1);
            return;
        }
        if (snake.contains(new Node(nr, nc))) {
            System.out.println(t + 1);
            return;
        }
        if (apple[nr][nc]) apple[nr][nc] = false;
        else snake.pollLast();
        move(nr, nc, d, t + 1);
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= apple.length || c < 0 || c >= apple.length;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) return false;
            Node node = (Node) obj;
            return Objects.equals(this.r, node.r) && this.c == node.c;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
