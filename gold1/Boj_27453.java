package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    귀엽기만 한 게 아닌 한별 양
    https://www.acmicpc.net/problem/27453
*/
public class Boj_27453 {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[4][N][M];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                switch (str.charAt(j)) {
                    case 'S':
                        queue.offer(new Node(i, j));
                        map[i][j] = 0;
                        break;
                    case 'H':
                        map[i][j] = 10;
                        break;
                    case 'X':
                        map[i][j] = -1;
                        break;
                    default:
                        map[i][j] = str.charAt(j) - '0';
                }
            }
        }
        System.out.println(bfs());
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j] + "\t");
//            }
//            System.out.println();
//        }
    }

    static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (map[node.r][node.c] > 9) {
                return node.t;
            }
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (map[nr][nc] < 0) continue;
                Node next = new Node(nr, nc, node.t + 1, map[nr][nc], node);
                if (next.equals(node.from)) continue;
                int misfortune = next.getMisfortune();
                if (map[nr][nc] > 9) {
                    queue.offer(next);
                    break;
                }
                if (misfortune > K) continue;
                if (visited[d][nr][nc]) continue;
                queue.offer(next);
                visited[d][nr][nc] = true;
            }
        }
        return -1;
    }

    static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    static class Node {
        int r, c, t;
        Node from;
        int[] misfortune = new int[3];

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Node(int r, int c, int t, int k, Node from) {
            this.r = r;
            this.c = c;
            this.t = t;
            this.misfortune = copyMisfortune(from.misfortune);
            this.misfortune[t % 3] = k;
            this.from = from;
        }

        private int[] copyMisfortune(int[] src) {
            int[] temp = new int[3];
            System.arraycopy(src, 0, temp, 0, 3);
            return temp;
        }

        public int getMisfortune() {
            int sum = 0;
            for (int n : misfortune) sum += n;
            return sum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.r, this.c);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) return false;
            Node node = (Node) obj;
            return Objects.equals(this.r, node.r) && this.c == node.c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + t + ", " + getMisfortune() + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_27453().solution();
    }
}
