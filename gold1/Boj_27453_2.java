package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    귀엽기만 한 게 아닌 한별 양
    https://www.acmicpc.net/problem/27453
*/
public class Boj_27453_2 {
    static int N, M, K;
    static char[][] map;
    static boolean[][][] visited;
    static Queue<Node> queue;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[4][N][M];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                // 시작 좌표 찾기
                if (map[i][j] == 'S') {
                    map[i][j] = '0';
                    queue.offer(new Node(i, j));
                    break;
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // 4방 탐색
            for (int d = 0; d < 4; d++) {
                // 이전에 있던 좌표라면 pass
                if (node.d == d) continue;
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                int nd = d % 2 == 0 ? d + 1 : d - 1;
                // 범위 벗어나거나 벽이라면 갈 수 없음
                if (isOutOfRange(nr, nc) || map[nr][nc] == 'X') continue;
                // 집에 도착하면 종료
                if (map[nr][nc] == 'H') return node.t + 1;
                // 이미 방문했거나 불행지수 초과하면 못 감
                if (visited[d][nr][nc] || getMisfortune(node.r, node.c, node.d, map[nr][nc] - '0') > K) continue;
                queue.offer(new Node(nr, nc, node.t + 1, nd));
                visited[d][nr][nc] = true;
            }
        }
        return -1;
    }

    private static int getMisfortune(int r, int c, int d, int nk) {
        // 다음 위치
        int misfortune = nk;
        // 현재 위치
        misfortune += (map[r][c] - '0');
        // 직전 위치
        if (d >= 0) misfortune += (map[r + deltas[d][0]][c + deltas[d][1]] - '0');
        return misfortune;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;

    }

    public static void main(String[] args) throws Exception {
        new Boj_27453_2().solution();
    }
}

class Node {
    int r, c, t, d;

    public Node(int r, int c) {
        this(r, c, 0, -1);
    }

    public Node(int r, int c, int t, int d) {
        this.r = r;
        this.c = c;
        this.t = t;
        this.d = d;
    }

    @Override
    public String toString() {
        return "(" + r + ", " + c + ", " + t + ")";
    }
}
