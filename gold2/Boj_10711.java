package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    모래성
    https://www.acmicpc.net/problem/10711
*/
public class Boj_10711 {
    static int H, W;
    static int[][] map;
    static Queue<Node> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        queue = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                if (str.charAt(j) == '.') {
                    map[i][j] = 0;
                    queue.offer(new Node(i, j));
                } else map[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }
        System.out.println(wave());
    }

    private static int wave() {
        int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int d = 0; d < 8; d++) {
                    int nr = node.r + deltas[d][0];
                    int nc = node.c + deltas[d][1];
                    if (!isInRange(nr, nc)) continue;
                    if (map[nr][nc] == 0 || map[nr][nc] == 9) continue;
                    map[nr][nc]--;
                    if (map[nr][nc] == 0) queue.offer(new Node(nr, nc));
                }
            }
        }
        return time - 1;
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_10711().solution();
    }
}
