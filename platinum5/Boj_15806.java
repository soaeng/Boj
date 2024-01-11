package platinum5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    영우의 기숙사 청소
    https://www.acmicpc.net/problem/15806
*/
public class Boj_15806 {
    static int N, M, K, t;
    static HashSet<Node> nodes;
    static boolean[][][] visited;
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        nodes = new HashSet<>(K);
        queue = new ArrayDeque<>();
        visited = new boolean[2][N][N];
        // 영우의 방에 있는 곰팡이의 위치
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            queue.offer(new Node(r, c));
        }
        // 검사관이 검사하는 방바닥의 위치
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            nodes.add(new Node(r, c));
        }
        System.out.println(bfs() ? "YES" : "NO");
    }

    private static boolean bfs() {
        int[][] deltas = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        int time = 0;
        while (!queue.isEmpty() && time <= t) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 8; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    int nt = (time + 1) % 2;
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[nt][nr][nc]) continue;
                    queue.offer(new Node(nr, nc));
                    visited[nt][nr][nc] = true;
                }
            }
            time++;
        }
        for (Node node : nodes) {
            if (visited[t % 2][node.r][node.c]) return true;
        }
        return false;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_15806().solution();
    }
}
