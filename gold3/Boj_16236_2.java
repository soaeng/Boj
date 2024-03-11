package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    아기 상어
    https://www.acmicpc.net/problem/16236
*/
public class Boj_16236_2 {
    static int N, time, count, size;
    static int[][] map;
    static Node shark;
    static int[][] deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        time = count = 0;
        size = 2;
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 9) {
                    map[r][c] = 0;
                    shark = new Node(r, c);
                }
            }
        }
        while (true) {
            if (!bfs()) break;
        }
        System.out.println(time);
    }

    private static boolean bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.r == o2.r) return o1.c - o2.c;
            return o1.r - o2.r;
        });
        boolean[][] visited = new boolean[N][N];
        queue.offer(shark);
        visited[shark.r][shark.c] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            int q = queue.size();
            time++;
            for (int i = 0; i < q; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[nr][nc]) continue;
                    // 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없다.
                    if (map[nr][nc] > size) continue;
                    if (map[nr][nc] > 0 && map[nr][nc] < size) {
                        pq.offer(new Node(nr, nc));
                    }
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                } // end of d
            } // end of i
            if (!pq.isEmpty()) {
                Node node = pq.poll();
                map[node.r][node.c] = 0;
                count++;
                if (count == size) {
                    size++;
                    count = 0;
                }
                shark = node;
                Boj_16236_2.time += time;
                return true;
            }
        } // end of while
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
        solution();
    }
}
