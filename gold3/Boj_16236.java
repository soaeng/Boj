package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    아기 상어
    https://www.acmicpc.net/problem/16236
*/
public class Boj_16236 {
    static int N, time = 0;
    static int[][] map;
    static Node shark;
    static boolean flag = false;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int fishes = 0;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Node(i, j, 0);
                    map[i][j] = 0;
                } else if (map[i][j] != 0) fishes++;
            }
        }
        while (fishes > 0 && !flag) {
            bfs(shark);
            fishes--;
        }
        System.out.println(time);
    }


    private static void bfs(Node shark) {
        int[][] deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        int[][] visited = new int[N][N];
        Queue<Node> queue = new LinkedList<>();
        int minR = N, minC = N, minD = N * N;
        queue.offer(shark);
        visited[shark.r][shark.c] = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && visited[nr][nc] == 0 && map[nr][nc] <= shark.size) {
                    visited[nr][nc] = visited[node.r][node.c] + 1;
                    queue.offer(new Node(nr, nc, visited[nr][nc]));
                    if (map[nr][nc] != 0 && map[nr][nc] < shark.size) {
                        if (minD > visited[nr][nc] - 1) {
                            minR = nr;
                            minC = nc;
                            minD = visited[nr][nc] - 1;
                        } else if (minD == visited[nr][nc] - 1) {
                            if (minR == nr) {
                                if (minC > nc) minC = nc;
                            } else if (minR > nr) {
                                minR = nr;
                                minC = nc;
                            }
                        }
                    }
                }
            }
        }
        if (minR != N && minC != N && minR != N * N) {
            map[minR][minC] = 0;
            time += minD;
            shark.r = minR;
            shark.c = minC;
            shark.count++;
            if (shark.count == shark.size) {
                shark.count = 0;
                shark.size++;
            }
        } else flag = true;
    }

    private static class Node {
        int r, c, size, count, d;

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.size = 2;
            this.count = 0;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_16236().solution();
    }
}
