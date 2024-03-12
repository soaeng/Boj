package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    뱀
    https://www.acmicpc.net/problem/3190
*/
public class Boj_3190 {
    static int N, direction = 0;
    static int[][] board;
    static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Map<Integer, Integer> directions;
    static Deque<Node> snake;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        board = new int[N][N];
        directions = new HashMap<>();
        snake = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 2;
        }
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            directions.put(Integer.parseInt(st.nextToken()), st.nextToken().equals("D") ? 1 : 3);
        }
        board[0][0] = 1;
        snake.add(new Node(0, 0));
        move(0, 0, 0);
    }

    private static void move(int r, int c, int t) {
        if (directions.get(t) != null) changeDirection(directions.get(t));
        int nr = r + deltas[direction][0];
        int nc = c + deltas[direction][1];
        if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 1) {
            System.out.println(t + 1);
            return;
        }
        if (board[nr][nc] != 2) {
            Node node = snake.poll();
            board[node.r][node.c] = 0;
        } else board[r][c] = 1;
        snake.offer(new Node(nr, nc));
        board[nr][nc] = 1;
        move(nr, nc, t + 1);
    }

    private static void changeDirection(int c) {
        direction = (direction + c) % 4;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_3190().solution();
    }
}
